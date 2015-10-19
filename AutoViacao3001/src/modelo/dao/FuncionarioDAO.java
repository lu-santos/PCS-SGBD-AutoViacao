package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Admin;
import modelo.entidade.Funcionario;

public class FuncionarioDAO extends BaseCrudDAO<Funcionario> {
	private final String tabelaFuncionario = "funcionario";
	private final String tabelaPessoa = "pessoa";
	private final String nomeDasColunasPessoa = "nome, data_nascimento, endereco, bairro, cep, estado, telefone_residencial, telefone_celular, cpf";
	private final String nomeDasColunasFuncionario = "cargo, salario, data_contratacao, cpf_funcionario";
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  
    public FuncionarioDAO(ConexaoDAO conexao) {
        super(conexao);
    }	
	
	@Override
	public String getQueryDeExiste(Funcionario entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaPessoa + " (" + nomeDasColunasPessoa + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?); INSERT INTO " + tabelaFuncionario + " (" + nomeDasColunasFuncionario + ") VALUES(?, ?, ?, ?)";
	}

	@Override
	public String getQueryDeAlteracao(Funcionario funcionario) {
		return "UPDATE " + tabelaPessoa + " SET nome = ?, data_nascimento = ?, endereco = ?, bairro = ?, cep = ?, estado = ?, telefone_residencial = ?, telefone_celular = ? WHERE cpf = ?; UPDATE " + tabelaFuncionario + " SET cargo = ?, salario = ?, data_contratacao = ? WHERE cpf_funcionario = ?";
	}

	@Override
	public String getQueryDeRemocao(Funcionario funcionario) {
		return "DELETE FROM " + tabelaFuncionario + " WHERE cpf_funcionario = '" + funcionario.getCpf() + "'; DELETE FROM " + tabelaPessoa + " WHERE cpf = '" + funcionario.getCpf() + "'";
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT " + nomeDasColunasPessoa + ", cargo, salario, data_contratacao FROM " + tabelaPessoa + " JOIN " + tabelaFuncionario + " ON cpf = cpf_funcionario";
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		return "SELECT " + nomeDasColunasPessoa + ", cargo, salario, data_contratacao FROM " + tabelaPessoa + " JOIN " + tabelaFuncionario + " ON cpf = cpf_funcionario WHERE cpf = '" + identificador + "'";
	}

	@Override
	public Funcionario getEntidade(ResultSet registro) {
		Funcionario funcionario;
        try {
        	funcionario = new Funcionario();
        	funcionario.setCpf(registro.getString("cpf").trim());
        	funcionario.setNome(registro.getString("nome").trim());
        	funcionario.setDataDeNascimento(DATETIME_FORMAT.format(registro.getDate(("data_nascimento"))));
        	funcionario.setEndereco(registro.getString("endereco"));
        	funcionario.setBairro(registro.getString("bairro"));
        	funcionario.setCep(registro.getString("cep").trim());
        	funcionario.setEstado(registro.getString("estado"));
        	funcionario.setTelefoneResidencial(registro.getString("telefone_residencial").trim());
        	funcionario.setTelefoneCelular(registro.getString("telefone_celular").trim());
        	funcionario.setCargo(registro.getString("cargo"));
        	funcionario.setSalario(String.valueOf(registro.getDouble("salario")));
        	funcionario.setDataDeContratacao(DATETIME_FORMAT.format(registro.getDate(("data_contratacao"))));
        	return funcionario;
        } catch (SQLException | ParseException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Funcionario entidade) {
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
            pst.setString(10, entidade.getCargo());
            pst.setDouble(11, entidade.getSalario());
            pst.setDate(12, new java.sql.Date(DATETIME_FORMAT.parse(entidade.getDataDeContratacao()).getTime()));
            pst.setString(13, entidade.getCpf());
        } catch (SQLException | ParseException ex) {
        	System.out.println("Erro ao incluir/alterar no banco - " + ex);
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	@Override
	public Funcionario metodoDeBusca(ResultSet registro, Funcionario entidade) {  
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

}
