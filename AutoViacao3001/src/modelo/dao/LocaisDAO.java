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

	public LocaisDAO(ConexaoDAO conexao) {
		super(conexao);
	}

	@Override
	public String getQueryDeExiste(Locais locais) {
		return "SELECT " + nomeDasColunasLocais + " FROM " + tabelaLocais + " WHERE id_local_destino = " + locais.getIdLocalDestino() + " AND id_local_origem = " + locais.getIdLocalOrigem();
	}

	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaLocais + " (" + nomeDasColunasLocais + ") VALUES(?, ?, ?)";
	}

	@Override
	public String getQueryDeAlteracao(Locais entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeRemocao(Locais entidade) {
		// TODO Auto-generated method stub
		return null;
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
        	locais.setIdLocalOrigem(registro.getInt("id_local_origem"));
        	locais.setIdLocalDestino(registro.getInt("id_local_destino"));
        	locais.setDistancia(registro.getDouble("distancia"));
        	
        	return locais;
        } catch (SQLException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Locais entidade) {
		try {
		    pst.setInt(1, entidade.getIdLocalDestino());
		    pst.setInt(2, entidade.getIdLocalOrigem());
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
