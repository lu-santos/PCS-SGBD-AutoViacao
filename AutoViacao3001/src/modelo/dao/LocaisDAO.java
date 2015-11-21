package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.DataUtil;
import modelo.entidade.Locais;
import modelo.entidade.Local;
import modelo.entidade.Viagem;

public class LocaisDAO extends BaseCrudDAO<Locais> {

	private final String tabelaLocais = "locais";
	private final String nomeDasColunasLocais = "id_local_destino, id_local_origem, distancia";

	private LocalDAO localDAO;
	
	public LocaisDAO() {}
	
	public LocaisDAO(ConexaoDAO conexao) {
		super(conexao);
	}

	@Override
	public String getQueryDeExiste(Locais locais) {
		return "SELECT " + nomeDasColunasLocais + " FROM " + tabelaLocais + " WHERE id_local_destino = " + locais.getLocalDeDestino().getId() + " AND id_local_origem = " + locais.getLocalDeOrigem().getId();
	}

	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaLocais + " (" + nomeDasColunasLocais + ") VALUES(?, ?, ?)";
	}

	@Override
	public String getQueryDeAlteracao(Locais locais) {
		return "UPDATE " + tabelaLocais + " SET id_local_destino = ?, id_local_origem = ?, distancia = ? WHERE id = " + locais.getIdLocais();
	}

	@Override
	public String getQueryDeRemocao(Locais locais) {
		return "DELETE FROM " + tabelaLocais + " WHERE id = " + locais.getIdLocais();
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT * FROM " + tabelaLocais;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		return "SELECT * FROM " + tabelaLocais + " WHERE id = " + identificador;
	}

	@Override
	public Locais getEntidade(ResultSet registro) {
		Locais locais;
        try {
        	locais = new Locais();
        	locais.setIdLocais(registro.getInt("id"));
        	localDAO = new LocalDAO(conexao);
        	locais.setLocalDeOrigem((localDAO.buscar(registro.getInt("id_local_origem"))));
        	locais.setLocalDeDestino((localDAO.buscar(registro.getInt("id_local_destino"))));
        	locais.setDistancia(String.valueOf(registro.getDouble("distancia"))); 	
        	return locais;
        } catch (SQLException ex) {
        	System.out.println("Erro ao pegar locais no banco - " + ex);
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Locais entidade) {
		try {
		    pst.setInt(1, entidade.getLocalDeDestino().getId());
		    pst.setInt(2, entidade.getLocalDeOrigem().getId());
		    pst.setDouble(3, entidade.getDistancia());
        } catch (SQLException ex) {
        	System.out.println("Erro ao incluir/alterar no banco - " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	@Override
	public Locais metodoDeBusca(ResultSet registro, Locais locais) {
		try {
			while (registro.next()) {
				locais = getEntidade(registro);
			}
		} catch (SQLException e) {
			System.out.println("Erro no método de busca: " + e.getMessage());
			e.printStackTrace();
		}
		return locais;
	}
	
	@Override
	public boolean incluir(Locais entidade) throws Exception {
		int inclusaoRealizada = 0;
		if (Consulta(getQueryDeExiste(entidade)).size() > 0) {
			return false;
		}
		else {
	        conectar = conexao.abrirConexao();
	        String query = getQueryDeInclusao();
	        PreparedStatement pst = conectar.prepareStatement(query);
	        incluirDadosNoBanco(pst, entidade);
	        inclusaoRealizada = pst.executeUpdate();
	        conexao.fecharConexao();
		}
        return inclusaoRealizada != 0;
    }
}
