package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Cliente;
import modelo.entidade.Local;

public class LocalDAO extends BaseCrudDAO<Local> {
	
	private final String tabelaLocal = "local";
	private final String nomeDasColunasLocal = "nome";

	public LocalDAO() {}
	
	public LocalDAO(ConexaoDAO conexao) {
		super(conexao);
	}

	@Override
	public String getQueryDeExiste(Local local) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaLocal + " (" + nomeDasColunasLocal + ") VALUES(?)";
	}

	@Override
	public String getQueryDeAlteracao(Local local) {
		return "UPDATE " + tabelaLocal + " SET nome = ? WHERE id = " + local.getId();
	}

	@Override
	public String getQueryDeRemocao(Local local) {
		return "DELETE FROM locais WHERE id_local_origem = " + local.getId() + " or id_local_destino = " + local.getId() 
				+ "; DELETE FROM " + tabelaLocal + " WHERE id = " + local.getId();
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT * FROM " + tabelaLocal;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		return "SELECT * FROM " + tabelaLocal + " WHERE ID = " + identificador; 
	}

	@Override
	public Local getEntidade(ResultSet registro) {
		Local local;
        try {
        	local = new Local();
        	local.setId(registro.getInt("id"));
        	local.setNome(registro.getString("nome"));
        	return local;
        } catch (SQLException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(PoltronaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Local entidade) {
		try {
		    pst.setString(1, entidade.getNome());
        } catch (SQLException ex) {
        	System.out.println("Erro ao incluir/alterar no banco - " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	@Override
	public Local metodoDeBusca(ResultSet registro, Local local) {
		try {
			while (registro.next()) {
				local = getEntidade(registro);
			}
		} catch (SQLException e) {
			System.out.println("Erro no método de busca: " + e.getMessage());
			e.printStackTrace();
		}
		return local;
	}
	
	public String getNomeLocal(Integer id){
		String nome = "";
		try {
			nome = buscar(id).getNome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nome;
	}
	
	public List<Local> destinoMaisProcurado() throws Exception {
		String query = "select id_local_destino as id, nome, count(id_local_destino) as total "
				+ "from viagem v join locais l " 
				+ "on v.id_locais = l.id "
				+ "join local lo "
				+ "on l.id_local_destino = lo.id "
				+ "group by id_local_destino, nome "
				+ "order by total desc";
		return Consulta(query);
	}
	
}
