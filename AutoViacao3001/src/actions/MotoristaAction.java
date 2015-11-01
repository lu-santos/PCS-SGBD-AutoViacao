package actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.dao.MotoristaDAO;
import modelo.dao.ConexaoPostgres;
import modelo.entidade.Motorista;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MotoristaAction extends ActionSupport{
	private Motorista motorista = new Motorista();
	private List<Motorista> motoristas;
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private MotoristaDAO mDAO = new MotoristaDAO(conexao);
	private String mensagem;
	private List<String> estados;
	
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public String adicionar() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			if (mDAO.incluir(this.motorista) == true) {
				mensagem = "Motorista cadastrado com sucesso.";	
			}
		} catch (Exception e) {
			if (e.getMessage().contains("duplicate key value violates unique constraint"))
				mensagem = "CPF já cadastrado. Preencha com outro CPF.";
			else
				mensagem = e.getMessage();
			return MotoristaAction.INPUT;
		}
		this.motorista = new Motorista();
		return MotoristaAction.SUCCESS ;
	}
	
	public String editar() {
		try {
			if(camposEmBranco() == true) {
				mensagem = "Preencha os campos obrigatórios";
				return ClienteAction.INPUT;
			}
			else if (mDAO.alterar(this.motorista) == true) {
				mensagem = "Alteração realizada com sucesso";
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		this.motorista = new Motorista();
		return MotoristaAction.SUCCESS;
	}
	
	public String visualizar() {
		try {
			this.motorista = mDAO.buscar(this.motorista.getCpf());
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return MotoristaAction.SUCCESS;
	}
	
	public String listarMotorista() {
		try {
			String query = "SELECT cpf, nome, data_nascimento, endereco, bairro, cep, estado, telefone_residencial, telefone_celular, salario, data_contratacao FROM pessoa JOIN motorista ON cpf = cpf_motorista";
			this.motoristas = mDAO.Consulta(query);
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return MotoristaAction.SUCCESS;
	}
	
	public String excluir() {
		try {
			if(mDAO.remover(motorista) == true) {
				mensagem = "Exclusão realizada com sucesso";
			}
			else {
				mensagem = "Falha na exclusão";
				return MotoristaAction.INPUT;
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
			e.printStackTrace();
		}
		return MotoristaAction.SUCCESS;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista funcionario) {
		this.motorista = funcionario;
	}
	
	public List<String> getEstados() {
		estados = MetodosAuxiliares.estadosBrasileiros();
		return estados;
	}
	
	public void setEstados(List<String> estados) {
		this.estados = estados;
	}
	
	public boolean camposEmBranco() {
		if (motorista.getNome().length() == 0 || motorista.getEndereco().length() == 0 || 
				motorista.getBairro().length() == 0 || motorista.getCep().length() == 0 ||
				motorista.getTelefoneResidencial().length() == 0 || 
				motorista.getTelefoneCelular().length() == 0 ||
				motorista.getSalario() == null || motorista.getSalario().isNaN() == true || motorista.getDataDeContratacao().length() == 0) {
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

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public void setMotoristas(List<Motorista> motoristas) {
		this.motoristas = motoristas;
	}
}
