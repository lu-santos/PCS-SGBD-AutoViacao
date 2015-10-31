package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Passagem;
import modelo.entidade.Viagem;
import util.DataUtil;

public class ViagemDAO extends BaseCrudDAO<Viagem>{
	
	private final String tabelaViagem = "viagem";
	private final String nomeDasColunasViagem = "data_hora_partida, data_hora_chegada, id_onibus, cpf_motorista, "
			+ "id_locais";
	
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
	public String getQueryDeAlteracao(Viagem entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeRemocao(Viagem entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT * FROM " + tabelaViagem;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		// TODO Auto-generated method stub
		return null;
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
        	viagem.setIdLocais(registro.getInt("id_locais"));
        	viagem.setIdOnibus(registro.getInt("id_onibus"));
        	viagem.setCpfMotorista(registro.getString("cpf_motorista"));
        	return viagem;
        } catch (SQLException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Viagem viagem) {
		try {
			pst.setTimestamp(1, new Timestamp(viagem.getDataHoraPartida().getTime()));
			pst.setTimestamp(2, new Timestamp(viagem.getDataHoraChegada().getTime()));
			pst.setInt(3, viagem.getIdOnibus());
			pst.setString(4, viagem.getCpfMotorista());
			pst.setInt(5, viagem.getIdLocais());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Viagem metodoDeBusca(ResultSet registro, Viagem entidade) {
		  try {
			  List<Passagem> passagens = new ArrayList<>();  
			  Integer idViagem = null;
			  while(registro.next()){
				  if(!idViagem.equals(registro.getInt("id_viagem")))
					  entidade = getEntidade(registro);
				  PassagemDAO passagemDAO = new PassagemDAO();
				  passagens.add(passagemDAO.getEntidade(registro));
				  idViagem = registro.getInt("id_viagem");
			  }
				  entidade.setPassagens(passagens);
		} catch (SQLException e) {
			System.out.println("Erro no método de busca: " + e.getMessage());
			e.printStackTrace();
		}
		  return entidade;
	}
	
	public boolean existeConflitoDeOnibus(Viagem viagem) throws Exception{
		String query = "select v.id_viagem from viagem v, onibus o where v.data_hora_partida < '" + viagem.getDataHoraPartidaString() + "' and "
				+ "v.data_hora_chegada > '" + viagem.getDataHoraPartidaString() + "' and v.id_onibus = " + viagem.getIdOnibus() + " union "
						+ "select v.id_viagem from viagem v, onibus o where v.data_hora_partida = '" + viagem.getDataHoraPartidaString() + 
						"' and v.id_onibus = " + viagem.getIdOnibus() + " union select v.id_viagem from viagem v, onibus o where "
								+ "v.data_hora_partida > '" + viagem.getDataHoraPartidaString() + "' and v.data_hora_partida < '"
										+ viagem.getDataHoraChegadaString() + "' and v.id_onibus = " + viagem.getIdOnibus() + ";";
		
		return existeAlgumResultado(query);		
	}
	
	public boolean existeConflitoDeMotorista(Viagem viagem) throws Exception{
		String query = "select v.id_viagem from viagem v, pessoa p, motorista m where v.data_hora_partida < '" + viagem.getDataHoraPartidaString()
				+ "' and v.data_hora_chegada > '" + viagem.getDataHoraPartidaString() + "' and p.cpf = '" + viagem.getCpfMotorista() + 
				"' and p.cpf = m.cpf_motorista union "
				+ "select v.id_viagem from viagem v, pessoa p, motorista m where v.data_hora_partida = '" + viagem.getDataHoraPartidaString() + 
				"' and p.cpf = '" + viagem.getCpfMotorista() + "' and p.cpf = m.cpf_motorista union select v.id_viagem from viagem v, pessoa p, motorista m "
						+ "where v.data_hora_partida > '" + viagem.getDataHoraPartidaString() + "' and v.data_hora_partida < '" + viagem.getDataHoraChegadaString() + 
						"' and p.cpf = '" + viagem.getCpfMotorista() + "' and p.cpf = m.cpf_motorista";
		
        return existeAlgumResultado(query);	
	}
	
	private boolean existeAlgumResultado(String query) throws Exception, SQLException {
		conectar = conexao.abrirConexao();

		PreparedStatement pst = conectar.prepareStatement(query);
		ResultSet registro = pst.executeQuery();

		if (registro.next()) {
			return true;
		}

		return false;
	}
}
