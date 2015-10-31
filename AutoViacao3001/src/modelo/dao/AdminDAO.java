package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Admin;

public class AdminDAO extends BaseCrudDAO<Admin> {

	private final String tabelaAdmin = "administrador";
	private final String tabelaPessoa = "pessoa";
	private final String nomeDasColunasPessoa = "cpf, nome, data_nascimento, endereco, bairro, cep, estado, telefone_residencial, telefone_celular";
	private final String nomeDasColunasAdmin = "cpf_adm, senha";
	
	public AdminDAO(ConexaoDAO conexao) {
		super(conexao);
		// TODO Auto-generated constructor stub
	}

	public String getQueryDeExiste(Admin admin) {
		return "SELECT DISTINCT * FROM " + tabelaAdmin + " WHERE cpf_adm = '" + admin.getCpf() + "' AND senha = '" + admin.getSenha() + "'";
	}

	@Override
	public String getQueryDeInclusao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeAlteracao(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeRemocao(Admin entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeListar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		return "SELECT " + nomeDasColunasPessoa + ", senha FROM " + tabelaPessoa + " NATURAL INNER JOIN " + tabelaAdmin + " WHERE cpf = '" + identificador + "'";
	}

	@Override
	public Admin getEntidade(ResultSet registro) {
		Admin admin;
        try {
        	admin = new Admin();
        	admin.setCpf(registro.getString("cpf"));
        	admin.setNome(registro.getString("nome"));
        	admin.setSenha(registro.getString("senha"));
            return admin;
        } catch (SQLException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Admin entidade) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Admin metodoDeBusca(ResultSet registro, Admin entidade) {
		  try {
			while(registro.next()){
				  entidade = getEntidade(registro);
			  }
		} catch (SQLException e) {
			System.out.println("Erro no método de busca: " + e.getMessage());
			e.printStackTrace();
		}
		  return entidade;
	}
	
	public boolean existe(Admin entidade) throws Exception {
        conectar = conexao.abrirConexao();
        String query = getQueryDeExiste(entidade);
        int existeNoBanco = 0;
        try{
            PreparedStatement pst = conectar.prepareStatement(query);
            ResultSet registro = pst.executeQuery();
            while(registro.next()){
            	existeNoBanco = registro.getRow();
            }
        }catch(SQLException e){
            System.out.println("Erro ao verificar se existe a tupla no banco: " + e.getMessage());
        }finally{
            conexao.fecharConexao();
        }        
        return existeNoBanco != 0;
    }

	
}
