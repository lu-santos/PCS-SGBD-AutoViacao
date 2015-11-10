package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import modelo.dao.ConexaoPostgres;
import modelo.dao.LocaisDAO;
import modelo.dao.LocalDAO;
import modelo.entidade.Cliente;
import modelo.entidade.Locais;
import modelo.entidade.Local;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LocalAction extends ActionSupport {
	
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private LocaisDAO locaisComDistanciaDAO = new LocaisDAO(conexao);
	private List<Locais> locais;
	private Locais localComDistancia = new Locais();
	private String mensagem;
	
	private LocalDAO localDAO = new LocalDAO(conexao);
	private Local local = new Local();
	private List<Local> listaDeLocaisSemDistancia;
	
	private String getQueryDeListarLocaisAposCadastro(int idOrigem) {
		return "SELECT distinct id, nome FROM local where id != " 
				+ idOrigem 
				+ " except select id_local_destino, nome from locais left join local "
				+ " on local.id = locais.id_local_destino where id_local_origem = "
				+ idOrigem;
	}
	
	public String adicionarLocal() {
		try {
			if (localDAO.incluir(this.local) == true) {
				mensagem = "Local cadastrado com sucesso.";	
				this.local = localDAO.Consulta("SELECT * FROM local where nome = '" + this.local.getNome() + "'").get(0);
				this.listaDeLocaisSemDistancia = localDAO.Consulta(getQueryDeListarLocaisAposCadastro(local.getId().intValue()));
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
			if (e.getMessage().contains("duplicate key value violates unique constraint"))
				mensagem = "Local já cadastrado. Preencha outro Local.";
			else
				mensagem = e.getMessage();
			return LocalAction.INPUT;
		}
		return LocalAction.SUCCESS ;
	}
	
	public String adicionarDistancia() {
		try {
			this.local = localDAO.buscar(localComDistancia.getIdLocalOrigem());			
			this.listaDeLocaisSemDistancia = localDAO.Consulta(getQueryDeListarLocaisAposCadastro(local.getId().intValue()));
			
			if (locaisComDistanciaDAO.incluir(this.localComDistancia) == true) {
				mensagem = "Distância cadastrada com sucesso.";			
				this.listaDeLocaisSemDistancia = localDAO.Consulta(getQueryDeListarLocaisAposCadastro(local.getId().intValue()));
			}
			else {
				mensagem = "Distância já cadastrada no banco. Escolha outro Destino";
			}			
		} catch (Exception e) {
			mensagem = e.getMessage();
			e.printStackTrace();
		}
		return LocalAction.SUCCESS ;
	}
	
	public String listarLocaisSemDistancia() {
		try {
			this.listaDeLocaisSemDistancia = localDAO.listar();
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return LocalAction.SUCCESS;
	}
	
	public String exibir(){
		return LocalAction.NONE;
	}
	
	public String listar() {
		try {
			this.locais = locaisComDistanciaDAO.listar();
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

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<Local> getListaDeLocaisSemDistancia() {
		return listaDeLocaisSemDistancia;
	}

	public void setListaDeLocaisSemDistancia(
			List<Local> listaDeLocaisSemDistancia) {
		this.listaDeLocaisSemDistancia = listaDeLocaisSemDistancia;
	}

	public Locais getLocalComDistancia() {
		return localComDistancia;
	}

	public void setLocalComDistancia(Locais localComDistancia) {
		this.localComDistancia = localComDistancia;
	}
	
}
