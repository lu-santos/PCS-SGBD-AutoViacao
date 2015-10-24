package actions;

import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.LocalDAO;
import modelo.entidade.Local;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LocalAction extends ActionSupport {
	
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private LocalDAO lDAO = new LocalDAO(conexao);
	private List<Local> locais;
	String mensagem;
	
	public String exibir(){
		return LocalAction.NONE;
	}
	
	public String listar() {
		try {
			this.locais = lDAO.listar();
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return LocalAction.SUCCESS;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}
	
}
