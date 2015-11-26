package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Viagem;
import util.DataUtil;

public class ViagemDAO extends BaseCrudDAO<Viagem>{
	
	private final String tabelaViagem = "viagem";
	private final String tabelaPassagem = "passagem";
	private final String nomeDasColunasViagem = "data_hora_partida, data_hora_chegada, id_onibus, cpf_motorista, "
			+ "id_locais";
	private OnibusDAO onibusDAO;
	private MotoristaDAO motoristaDAO;
	private LocaisDAO locaisDAO;
	
	public ViagemDAO() {}

	public ViagemDAO(ConexaoDAO conexao) {
		super(conexao);
	}

	@Override
	public String getQueryDeExiste(Viagem entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaViagem + " (" + nomeDasColunasViagem + ") VALUES(?, ?, ?, ?, ?)";
	}

	@Override
	public String getQueryDeAlteracao(Viagem viagem) {
		return "UPDATE " + tabelaViagem + " SET data_hora_partida = ?, data_hora_chegada = ?, id_onibus = ?, cpf_motorista = ?, id_locais = ? WHERE id_viagem = " + viagem.getIdViagem();
	}

	@Override
	public String getQueryDeRemocao(Viagem viagem) {
		return "DELETE FROM " + tabelaViagem + " WHERE id_viagem = " + viagem.getIdViagem();
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT * FROM " + tabelaViagem;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		return "SELECT * FROM " + tabelaViagem + " WHERE id_viagem = " + identificador;
	}

	@Override
	public Viagem getEntidade(ResultSet registro) {
		Viagem viagem;
        try {
        	viagem = new Viagem();
        	viagem.setIdViagem(registro.getInt("id_viagem"));
        	try {
				viagem.setDataHoraPartida(DataUtil.converterDataComHoraParaString(registro.getTimestamp("data_hora_partida")));
				viagem.setDataHoraChegada(DataUtil.converterDataComHoraParaString(registro.getTimestamp("data_hora_chegada")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	onibusDAO = new OnibusDAO(conexao);
        	motoristaDAO = new MotoristaDAO(conexao);
        	locaisDAO =  new LocaisDAO(conexao);
        	viagem.setOnibus(onibusDAO.buscar(registro.getInt("id_onibus")));
        	viagem.setMotorista(motoristaDAO.buscar(registro.getString("cpf_motorista")));
        	viagem.setLocais(locaisDAO.buscar(registro.getInt("id_locais")));
        	return viagem;
        } catch (SQLException ex) {
        	System.out.println("Erro ao pegar viagem no banco - " + ex);
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Viagem viagem) {
		try {
			pst.setTimestamp(1, new Timestamp(viagem.getDataHoraPartida().getTime()));
			pst.setTimestamp(2, new Timestamp(viagem.getDataHoraChegada().getTime()));
			pst.setInt(3, viagem.getOnibus().getIdOnibus());
			pst.setString(4, viagem.getMotorista().getCpf());
			pst.setInt(5, viagem.getLocais().getIdLocais());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Viagem metodoDeBusca(ResultSet registro, Viagem viagem) {
		try {
			while (registro.next()) {
				viagem = getEntidade(registro);
			}
		} catch (SQLException e) {
			System.out.println("Erro no método de busca: " + e.getMessage());
			e.printStackTrace();
		}
		return viagem;
	}

	public boolean existeConflitoDeOnibus(Viagem viagem) throws Exception{
		String query = "select v.id_viagem from viagem v where v.data_hora_partida < '" + viagem.getDataHoraPartidaString() + "' and "
				+ "v.data_hora_chegada > '" + viagem.getDataHoraPartidaString() + "' and v.id_onibus = " + viagem.getOnibus().getIdOnibus() + " union "
						+ "select v.id_viagem from viagem v where v.data_hora_partida = '" + viagem.getDataHoraPartidaString() + 
						"' and v.id_onibus = " + viagem.getOnibus().getIdOnibus() + " union select v.id_viagem from viagem v where "
								+ "v.data_hora_partida > '" + viagem.getDataHoraPartidaString() + "' and v.data_hora_partida < '"
										+ viagem.getDataHoraChegadaString() + "' and v.id_onibus = " + viagem.getOnibus().getIdOnibus() + ";";
		
		return existeAlgumResultado(query);		
	}
	
	public boolean existeConflitoDeMotorista(Viagem viagem) throws Exception{
		String query = "select v.id_viagem from viagem v, motorista m where v.data_hora_partida < '" + viagem.getDataHoraPartidaString()
				+ "' and v.data_hora_chegada > '" + viagem.getDataHoraPartidaString() + "' and v.cpf_motorista = '" + viagem.getMotorista().getCpf() + 
				"' union select v.id_viagem from viagem v, motorista m where v.data_hora_partida = '" + viagem.getDataHoraPartidaString() + 
				"' and v.cpf_motorista = '" + viagem.getMotorista().getCpf() + "' union select v.id_viagem from viagem v, motorista m "
						+ "where v.data_hora_partida > '" + viagem.getDataHoraPartidaString() + "' and v.data_hora_partida < '" + viagem.getDataHoraChegadaString() + 
						"' and v.cpf_motorista = '" + viagem.getMotorista().getCpf() + "'";
		
        return existeAlgumResultado(query);	
	}
	
	public boolean existeConflitoDeOnibusNaAtualizacao(Viagem viagem) throws Exception{
		String query = "select v.id_viagem from viagem v where v.data_hora_partida < '" + viagem.getDataHoraPartidaString() + "' and "
				+ "v.data_hora_chegada > '" + viagem.getDataHoraPartidaString() + "' and v.id_onibus = " + viagem.getOnibus().getIdOnibus() + ""
						+ " and v.id_viagem <> " + viagem.getIdViagem() + " union "
						+ "select v.id_viagem from viagem v where v.data_hora_partida = '" + viagem.getDataHoraPartidaString() + 
						"' and v.id_onibus = " + viagem.getOnibus().getIdOnibus() + " and v.id_viagem <> " + viagem.getIdViagem() + " union select v.id_viagem from viagem v where "
								+ "v.data_hora_partida > '" + viagem.getDataHoraPartidaString() + "' and v.data_hora_partida < '"
										+ viagem.getDataHoraChegadaString() + "' and v.id_onibus = " + viagem.getOnibus().getIdOnibus() + " and v.id_viagem <> " + viagem.getIdViagem();
		
		return existeAlgumResultado(query);		
	}
	
	public boolean existeConflitoDeMotoristaNaAtualizacao(Viagem viagem) throws Exception{
		String query = "select v.id_viagem from viagem v, motorista m where v.data_hora_partida < '" + viagem.getDataHoraPartidaString()
				+ "' and v.data_hora_chegada > '" + viagem.getDataHoraPartidaString() + "' and v.cpf_motorista = '" + viagem.getMotorista().getCpf() + 
				"' and v.id_viagem <> " + viagem.getIdViagem() + " union select v.id_viagem from viagem v, motorista m where v.data_hora_partida = '" + viagem.getDataHoraPartidaString() + 
				"' and v.cpf_motorista = '" + viagem.getMotorista().getCpf() + "' and v.id_viagem <> " + viagem.getIdViagem() + " union select v.id_viagem from viagem v, motorista m "
						+ "where v.data_hora_partida > '" + viagem.getDataHoraPartidaString() + "' and v.data_hora_partida < '" + viagem.getDataHoraChegadaString() + 
						"' and v.cpf_motorista = '" + viagem.getMotorista().getCpf() + "' and v.id_viagem <> " + viagem.getIdViagem();
		
        return existeAlgumResultado(query);	
	}
	
	private boolean existeAlgumResultado(String query) throws Exception, SQLException {
		conectar = conexao.abrirConexao();

		PreparedStatement pst = conectar.prepareStatement(query);
		ResultSet registro = pst.executeQuery();
		
		conexao.fecharConexao();
		
		if (registro.next()) {
			return true;
		}

		return false;
	}
	
	public List<Viagem> listarViagensSemPassagem() throws Exception{
		String query = "SELECT * FROM " + tabelaViagem + " EXCEPT (SELECT v.id_viagem, v.data_hora_partida, v.data_hora_chegada, v.id_onibus, v.cpf_motorista, v.id_locais from " 
	+ tabelaPassagem + " p, " + tabelaViagem + " v WHERE v.id_viagem = p.id_viagem)";
		return Consulta(query);
	}
	
	public List<Viagem> listarViagensMotorista(String cpfMotorista) throws Exception{
		String query = "SELECT * FROM " + tabelaViagem + " WHERE cpf_motorista = '" + cpfMotorista + "' ORDER BY data_hora_partida";
		return Consulta(query);
	}
	
	public List<Viagem> listarViagensOnibus(Integer idOnibus) throws Exception{
		String query = "SELECT * FROM " + tabelaViagem + " WHERE id_onibus = " + idOnibus + " ORDER BY data_hora_partida";
		return Consulta(query);
	}
	
	public List<Viagem> ListarResultadoDaConsultaViagens(Integer idLocais, String data) throws Exception {
		String query = "SELECT * FROM " + tabelaViagem + " WHERE id_locais = " + idLocais + " and data_hora_partida >= '" + data + "' ORDER BY data_hora_partida";
		return Consulta(query);
	}
	
	public List<Viagem> listarResultadoDaConsultaViagensPorLocais(Integer idLocais) throws Exception{
		String query = "SELECT * FROM " + tabelaViagem + " WHERE id_locais = " + idLocais + " ORDER BY data_hora_partida";
		return Consulta(query);
	}
	
	public List<Viagem> listarResultadoDaConsultaViagemPorData(String data) throws Exception{
		String query = "SELECT * FROM " + tabelaViagem + " WHERE data_hora_partida >= '" + data + "' ORDER BY data_hora_partida";
		return Consulta(query);
	}
}
