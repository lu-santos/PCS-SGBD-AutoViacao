package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Motorista;

public class MotoristaDAO extends BaseCrudDAO<Motorista> {
	private final String tabelaMotorista = "motorista";
	private final String tabelaPessoa = "pessoa";
	private final String nomeDasColunasPessoa = "nome, data_nascimento, endereco, bairro, cidade, cep, estado, telefone_residencial, telefone_celular, cpf";
	private final String nomeDasColunasMotorista = "salario, data_contratacao, cpf_motorista";
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  
    public MotoristaDAO(ConexaoDAO conexao) {
        super(conexao);
    }	
	
	@Override
	public String getQueryDeExiste(Motorista entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaPessoa + " (" + nomeDasColunasPessoa + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); INSERT INTO " + tabelaMotorista + " (" + nomeDasColunasMotorista + ") VALUES(?, ?, ?)";
	}

	@Override
	public String getQueryDeAlteracao(Motorista motorista) {
		return "UPDATE " + tabelaPessoa + " SET nome = ?, data_nascimento = ?, endereco = ?, bairro = ?, cidade = ?, cep = ?, estado = ?, telefone_residencial = ?, telefone_celular = ? WHERE cpf = ?; UPDATE " + tabelaMotorista + " SET salario = ?, data_contratacao = ? WHERE cpf_motorista = ?";
	}

	@Override
	public String getQueryDeRemocao(Motorista motorista) {
		return "DELETE FROM " + tabelaMotorista + " WHERE cpf_motorista = '" + motorista.getCpf() + "'; DELETE FROM " + tabelaPessoa + " WHERE cpf = '" + motorista.getCpf() + "'";
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT " + nomeDasColunasPessoa + ", salario, data_contratacao FROM " + tabelaPessoa + " JOIN " + tabelaMotorista + " ON cpf = cpf_motorista";
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		return "SELECT " + nomeDasColunasPessoa + ", salario, data_contratacao FROM " + tabelaPessoa + " JOIN " + tabelaMotorista + " ON cpf = cpf_motorista WHERE cpf = '" + identificador + "'";
	}

	@Override
	public Motorista getEntidade(ResultSet registro) {
		Motorista motorista;
        try {
        	motorista = new Motorista();
        	motorista.setCpf(registro.getString("cpf").trim());
        	motorista.setNome(registro.getString("nome").trim());
        	motorista.setDataDeNascimento(DATETIME_FORMAT.format(registro.getDate(("data_nascimento"))));
        	motorista.setEndereco(registro.getString("endereco"));
        	motorista.setBairro(registro.getString("bairro"));
        	motorista.setCidade(registro.getString("cidade"));
        	motorista.setCep(registro.getString("cep").trim());
        	motorista.setEstado(registro.getString("estado"));
        	motorista.setTelefoneResidencial(registro.getString("telefone_residencial").trim());
        	motorista.setTelefoneCelular(registro.getString("telefone_celular").trim());
        	motorista.setSalario(String.valueOf(registro.getDouble("salario")));
        	motorista.setDataDeContratacao(DATETIME_FORMAT.format(registro.getDate(("data_contratacao"))));
        	return motorista;
        } catch (SQLException | ParseException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(MotoristaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Motorista motorista) {
		try {
		    pst.setString(1, motorista.getNome());
		    pst.setDate(2, new java.sql.Date(DATETIME_FORMAT.parse(motorista.getDataDeNascimento()).getTime()));
		    pst.setString(3, motorista.getEndereco());
		    pst.setString(4, motorista.getBairro());
		    pst.setString(5, motorista.getCidade());
		    pst.setString(6, motorista.getCep());
		    pst.setString(7, motorista.getEstado());
		    pst.setString(8, motorista.getTelefoneResidencial());
		    pst.setString(9, motorista.getTelefoneCelular());
            pst.setString(10, motorista.getCpf());
            pst.setDouble(11, motorista.getSalario());
            pst.setDate(12, new java.sql.Date(DATETIME_FORMAT.parse(motorista.getDataDeContratacao()).getTime()));
            pst.setString(13, motorista.getCpf());
        } catch (SQLException | ParseException ex) {
        	System.out.println("Erro ao incluir/alterar no banco - " + ex);
            Logger.getLogger(MotoristaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	@Override
	public Motorista metodoDeBusca(ResultSet registro, Motorista motorista) {  
		try {
			while(registro.next()){
				  motorista = getEntidade(registro);
			  }
		} catch (SQLException e) {
			System.out.println("Erro no método de busca: " + e.getMessage());
			e.printStackTrace();
		}
		  return motorista;
	}
	
	public List<Motorista> listarMotoristas() throws Exception{
		String query = "SELECT cpf, nome, data_nascimento, endereco, bairro, cep, estado, telefone_residencial, telefone_celular, salario, data_contratacao FROM pessoa JOIN motorista ON cpf = cpf_motorista";
		return Consulta(query);
	}

}
