package actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.dao.ConexaoPostgres;
import modelo.dao.FuncionarioDAO;
import modelo.dao.OnibusDAO;
import modelo.entidade.Funcionario;
import modelo.entidade.Onibus;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class OnibusAction extends ActionSupport{
	private Onibus onibus = new Onibus();
	private List<Onibus> listaDeOnibus;
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private OnibusDAO oDAO = new OnibusDAO(conexao);
	private String mensagem;
	
	public String adicionar() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			if (oDAO.incluir(this.onibus) == true) {
				mensagem = "�nibus cadastrado com sucesso.";	
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
			return OnibusAction.INPUT;
		}
		this.onibus = new Onibus();
		return OnibusAction.SUCCESS ;
	}
	
	public String editar() {
		try {
			if(camposEmBranco() == true) {
				mensagem = "Preencha os campos obrigat�rios";
				return ClienteAction.INPUT;
			}
			else if (oDAO.alterar(this.onibus) == true) {
				mensagem = "Altera��o realizada com sucesso";
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		this.onibus = new Onibus();
		return OnibusAction.SUCCESS;
	}
	
	public String visualizar() {
		try {
			this.onibus = oDAO.buscar(this.onibus.getIdOnibus());
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return OnibusAction.SUCCESS;
	}
	
	public String listar() {
		try {
			this.listaDeOnibus = oDAO.listar();
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return OnibusAction.SUCCESS;
	}
	
	public boolean camposEmBranco() {
		if (onibus.getPlaca().length() == 0 || onibus.getModelo().length() == 0 || 
				onibus.getFabricante().length() == 0 || onibus.getCapacidade() == null ||
						onibus.getAno() == null){
			return true;
		}
		return false;
	}

	public Onibus getOnibus() {
		return onibus;
	}

	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}

	public List<Onibus> getListaDeOnibus() {
		return listaDeOnibus;
	}

	public void setListaDeOnibus(List<Onibus> listaDeOnibus) {
		this.listaDeOnibus = listaDeOnibus;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
