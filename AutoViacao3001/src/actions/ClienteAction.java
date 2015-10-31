package actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.dao.ClienteDAO;
import modelo.dao.ConexaoPostgres;
import modelo.entidade.Cliente;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ClienteAction extends ActionSupport{
	private Cliente cliente = new Cliente();
	private List<Cliente> clientes;
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private ClienteDAO cDAO = new ClienteDAO(conexao);
	private String mensagem;
	private List<String> estados;
	
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public String adicionar() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			if (cDAO.incluir(this.cliente) == true) {
				mensagem = "Cliente cadastrado com sucesso.";	
			}
		} catch (Exception e) {
			if (e.getMessage().contains("duplicate key value violates unique constraint"))
				mensagem = "CPF já cadastrado. Preencha com outro CPF.";
			else
				mensagem = e.getMessage();
			return ClienteAction.INPUT;
		}
		this.cliente = new Cliente();
		if(session.getAttribute("papel") != null && session.getAttribute("papel").equals("admin"))
			return "sucessoAdmin";
		return ClienteAction.SUCCESS ;
	}
	
	public String visualizar() {
		try {
			this.cliente = cDAO.buscar(this.cliente.getCpf());
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return ClienteAction.SUCCESS;
	}
	
	public String editar() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			if(camposEmBranco() == true) {
				mensagem = "Preencha os campos obrigatórios";
				return ClienteAction.INPUT;
			}
			else if (cDAO.alterar(this.cliente) == true) {
				if(session.getAttribute("papel").equals("cliente"))
					session.setAttribute("usuario", this.cliente);
				mensagem = "Alteração realizada com sucesso";
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		this.cliente = new Cliente();
		if(session.getAttribute("papel").equals("admin"))
			return "sucessoAdmin";
		return ClienteAction.SUCCESS;
	}
	
	public String excluir() {
		try {
			if(cDAO.remover(cliente) == true) {
				mensagem = "Exclusão realizada com sucesso";
			}
			else {
				mensagem = "Falha na exclusão";
				return ClienteAction.INPUT;
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
			e.printStackTrace();
		}
		return ClienteAction.SUCCESS;
	}
	
	public String listar() {
		try {
			this.clientes = cDAO.listar();
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return ClienteAction.SUCCESS;
	}

	public List<String> getEstados() {
		return MetodosAuxiliares.estadosBrasileiros();
	}
	
	public boolean camposEmBranco() {
		if (cliente.getNome().length() == 0 || cliente.getEndereco().length() == 0 || 
				cliente.getBairro().length() == 0 || cliente.getCep().length() == 0 ||
				cliente.getTelefoneResidencial().length() == 0 || 
				cliente.getTelefoneCelular().length() == 0 || cliente.getSenha().length() == 0) {
			return true;
		}
		return false;
	}

	public void setEstados(List<String> Estados) {
		this.estados = estados;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}
