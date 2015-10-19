package actions;


import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import modelo.dao.AdminDAO;
import modelo.dao.ClienteDAO;
import modelo.dao.ConexaoPostgres;
import modelo.entidade.Cliente;
import modelo.entidade.Admin;


@SuppressWarnings("serial")
public class AutenticacaoAction extends ActionSupport {
	private String cpf;
	private String senha;
	
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private ClienteDAO cDAO = new ClienteDAO(conexao);
	private AdminDAO aDAO = new AdminDAO(conexao);
	
	private String mensagem;
	
	private String autenticaoAceita = "sim";
	
	public String login() throws Exception {
		if(cDAO.existe(new Cliente(cpf, senha))) {
			alteracaoNosAtributosDaSessao("cliente");
			return "cliente";
		} else if(aDAO.existe(new Admin(cpf, senha))) { 
			alteracaoNosAtributosDaSessao("admin");
			return "admin";
		} else {
			mensagem = "Usuário e/ou senha incorretos.";
			return AutenticacaoAction.INPUT;
		}
	}
	
	public String logout () {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("usuario");
		this.mensagem = "Até logo!";
		session.invalidate();
		return AutenticacaoAction.SUCCESS;
	}
	
	private void alteracaoNosAtributosDaSessao(String papelDoUsuario) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession(true);
		if(papelDoUsuario.equals("admin")) {
			Admin admin = aDAO.buscar(cpf);
			session.setAttribute("usuario", admin);
		}
		else if (papelDoUsuario.equals("cliente")) {
			Cliente cliente = cDAO.buscar(cpf);
			session.setAttribute("usuario", cliente);
		}
		session.setAttribute("papel", papelDoUsuario);
		session.setAttribute("autenticado", autenticaoAceita);
		mensagem = "Autenticação realizada com sucesso.";
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
