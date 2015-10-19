package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Admin;
import modelo.entidade.Cliente;
import modelo.entidade.Passagem;

public class ClienteDAO extends BaseCrudDAO<Cliente> {
	
	private final String tabelaCliente = "cliente";
	private final String tabelaPessoa = "pessoa";
	private final String nomeDasColunasPessoa = "nome, data_nascimento, endereco, bairro, cep, estado, telefone_residencial, telefone_celular, cpf";
	private final String nomeDasColunasCliente = "senha, cpf_cliente";
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  
    public ClienteDAO(ConexaoDAO conexao) {
        super(conexao);
    }

    public String getQueryDeExiste(Cliente cliente) {
    	return "SELECT DISTINCT * FROM " + tabelaCliente + " WHERE cpf_cliente = '" + cliente.getCpf() + "' AND senha = '" + cliente.getSenha() + "'";
    }
    
	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaPessoa + " (" + nomeDasColunasPessoa + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?); INSERT INTO " + tabelaCliente + " (" + nomeDasColunasCliente + ") VALUES(?, ?)";
	}
	
	@Override
	public String getQueryDeAlteracao(Cliente cliente) {
		return "UPDATE " + tabelaPessoa + " SET nome = ?, data_nascimento = ?, endereco = ?, bairro = ?, cep = ?, estado = ?, telefone_residencial = ?, telefone_celular = ? WHERE cpf = ?; UPDATE " + tabelaCliente + " SET senha = ? WHERE cpf_cliente = ?";
	}
	
	@Override
	public String getQueryDeRemocao(Cliente cliente) {
		return "DELETE FROM " + tabelaCliente + " WHERE cpf_cliente = '" + cliente.getCpf() + "'; DELETE FROM " + tabelaPessoa + " WHERE cpf = '" + cliente.getCpf() + "'";
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT " + nomeDasColunasPessoa + ", senha FROM " + tabelaPessoa + " JOIN " + tabelaCliente + " ON cpf = cpf_cliente";
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		return "SELECT * FROM " + tabelaPessoa + " JOIN " + tabelaCliente + " ON cpf = cpf_cliente AND cpf = '" + identificador + "' LEFT JOIN passagem USING (cpf_cliente)";
	}

	@Override
	public Cliente metodoDeBusca(ResultSet registro, Cliente entidade) {  
		try {
			List<Passagem> passagens = new ArrayList<>();
			String cpf = "";
			while(registro.next()){
				if(!cpf.equals(registro.getString("cpf").trim())) {
					entidade = getEntidade(registro);
				}
			  	PassagemDAO passagemDAO = new PassagemDAO();
			  	passagens.add(passagemDAO.getEntidade(registro));
			  	cpf = registro.getString("cpf").trim();
			  }
			entidade.setPassagens(passagens);			
		} catch (SQLException e) {
			System.out.println("Erro no método de busca: " + e.getMessage());
			e.printStackTrace();
		}
		  return entidade;
	}
	
	@Override
	public Cliente getEntidade(ResultSet registro) {
		Cliente cliente;
        try {
        	cliente = new Cliente();
        	cliente.setCpf(registro.getString("cpf").trim());
        	cliente.setNome(registro.getString("nome").trim());
        	cliente.setDataDeNascimento(DATETIME_FORMAT.format(registro.getDate(("data_nascimento"))));
        	cliente.setEndereco(registro.getString("endereco"));
        	cliente.setBairro(registro.getString("bairro"));
        	cliente.setCep(registro.getString("cep").trim());
        	cliente.setEstado(registro.getString("estado"));
        	cliente.setTelefoneResidencial(registro.getString("telefone_residencial").trim());
        	cliente.setTelefoneCelular(registro.getString("telefone_celular").trim());
        	cliente.setSenha(registro.getString("senha"));
        	return cliente;
        } catch (SQLException | ParseException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Cliente entidade) {
		try {
		    pst.setString(1, entidade.getNome());
		    pst.setDate(2, new java.sql.Date(DATETIME_FORMAT.parse(entidade.getDataDeNascimento()).getTime()));
		    pst.setString(3, entidade.getEndereco());
		    pst.setString(4, entidade.getBairro());
		    pst.setString(5, entidade.getCep());
		    pst.setString(6, entidade.getEstado());
		    pst.setString(7, entidade.getTelefoneResidencial());
		    pst.setString(8, entidade.getTelefoneCelular());
            pst.setString(9, entidade.getCpf());
            pst.setString(10, entidade.getSenha());
            pst.setString(11, entidade.getCpf());
        } catch (SQLException | ParseException ex) {
        	System.out.println("Erro ao incluir/alterar no banco - " + ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	public boolean existe(Cliente entidade) throws Exception {
        conectar = conexao.abrirConexao();
        String query = getQueryDeExiste(entidade);
        int exiteNoBanco = 0;
        try{
            PreparedStatement pst = conectar.prepareStatement(query);
            ResultSet registro = pst.executeQuery();
            while(registro.next()){
            	exiteNoBanco = registro.getRow();
            }
        }catch(SQLException e){
            System.out.println("Erro ao verificar se existe a tupla no banco: " + e.getMessage());
        }finally{
            conexao.fecharConexao();
        }        
        return exiteNoBanco != 0;
    }
}
