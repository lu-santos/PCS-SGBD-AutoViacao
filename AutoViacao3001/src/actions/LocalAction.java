package actions;

import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.LocaisDAO;
import modelo.entidade.Locais;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LocalAction extends ActionSupport {
	
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private LocaisDAO lDAO = new LocaisDAO(conexao);
	private List<Locais> locais;
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

	public List<Locais> getLocais() {
		return locais;
	}

	public void setLocais(List<Locais> locais) {
		this.locais = locais;
	}
	
}
