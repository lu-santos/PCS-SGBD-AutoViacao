package actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.dao.FuncionarioDAO;
import modelo.dao.ConexaoPostgres;
import modelo.entidade.Funcionario;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FuncionarioAction extends ActionSupport{
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios;
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private FuncionarioDAO fDAO = new FuncionarioDAO(conexao);
	private String mensagem;
	private List<String> estados;
	
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public String adicionar() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			if (fDAO.incluir(this.funcionario) == true) {
				mensagem = "Funcionário cadastrado com sucesso.";	
			}
		} catch (Exception e) {
			if (e.getMessage().contains("duplicate key value violates unique constraint"))
				mensagem = "CPF já cadastrado. Preencha com outro cpf.";
			else
				mensagem = e.getMessage();
			return FuncionarioAction.INPUT;
		}
		this.funcionario = new Funcionario();
		return FuncionarioAction.SUCCESS ;
	}
	
	public String editar() {
		try {
			if(camposEmBranco() == true) {
				mensagem = "Preencha os campos obrigatórios";
				return ClienteAction.INPUT;
			}
			else if (fDAO.alterar(this.funcionario) == true) {
				mensagem = "Alteração realizada com sucesso";
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		this.funcionario = new Funcionario();
		return FuncionarioAction.SUCCESS;
	}
	
	public String visualizar() {
		try {
			this.funcionario = fDAO.buscar(this.funcionario.getCpf());
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return FuncionarioAction.SUCCESS;
	}
	
	public String listarMotorista() {
		try {
			String query = "SELECT cpf, nome, data_nascimento, endereco, bairro, cep, estado, telefone_residencial, telefone_celular, cargo, salario, data_contratacao FROM pessoa JOIN funcionario ON cpf = cpf_funcionario WHERE cargo = 'motorista'  OR cargo = 'MOTORISTA'";
			this.funcionarios = fDAO.Consulta(query);
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return FuncionarioAction.SUCCESS;
	}
	
	public String excluir() {
		try {
			if(fDAO.remover(funcionario) == true) {
				mensagem = "Exclusão realizada com sucesso";
			}
			else {
				mensagem = "Falha na exclusão";
				return FuncionarioAction.INPUT;
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
			e.printStackTrace();
		}
		return FuncionarioAction.SUCCESS;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public List<String> getEstados() {
		return MetodosAuxiliares.estadosBrasileiro();
	}
	
	public void setEstados(List<String> Estados) {
		this.estados = estados;
	}
	
	public boolean camposEmBranco() {
		if (funcionario.getNome().length() == 0 || funcionario.getEndereco().length() == 0 || 
				funcionario.getBairro().length() == 0 || funcionario.getCep().length() == 0 ||
				funcionario.getTelefoneResidencial().length() == 0 || 
				funcionario.getTelefoneCelular().length() == 0 || funcionario.getCargo().length() == 0 ||
				funcionario.getSalario() == null || funcionario.getSalario().isNaN() == true || funcionario.getDataDeContratacao().length() == 0) {
			return true;
		}
		return false;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
}
