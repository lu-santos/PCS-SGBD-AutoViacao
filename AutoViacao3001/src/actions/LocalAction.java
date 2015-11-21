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
import modelo.entidade.Viagem;

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
			this.local = localDAO.buscar(localComDistancia.getLocalDeOrigem().getId());			
			this.listaDeLocaisSemDistancia = localDAO.Consulta(getQueryDeListarLocaisAposCadastro(local.getId().intValue()));
			
			if (locaisComDistanciaDAO.incluir(this.localComDistancia) == true) {
				mensagem = "Distância cadastrada com sucesso.";			
				carregarAlteracao();
			}
			else {
				carregarAlteracao();
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
	
	public String listarDestinoMaisProcurado() {
		try {
			this.listaDeLocaisSemDistancia = localDAO.destinoMaisProcurado();
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return LocalAction.SUCCESS;
	}
	
	public String excluirLocal() {
		try {
			this.local = localDAO.buscar(local.getId());
			if(localDAO.remover(local) == true) {
				this.listaDeLocaisSemDistancia = localDAO.listar();
				mensagem = "Exclusão realizada com sucesso";
			}
			else {
				this.listaDeLocaisSemDistancia = localDAO.listar();
				mensagem = "Falha na exclusão";
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return LocalAction.SUCCESS;
	}
	
	public String editarLocal() {
		try {
			if(camposEmBranco() == true) {
				mensagem = "Não foram preenchidos todos os campos obrigatórios";
				this.local = localDAO.buscar(local.getId());
				carregarAlteracao();
				return LocalAction.INPUT;
			}		
			
			else if(localDAO.alterar(local) == true) {
				this.local = localDAO.buscar(local.getId());
				mensagem = "Alteração Realizada com Sucesso";
				carregarAlteracao();
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao alterar nome do local : " + e.getMessage());
			if (e.getMessage().contains("duplicate key value violates unique constraint"))
				mensagem = "Local já cadastrado. Preencha outro Local.";
			else
				mensagem = e.getMessage();
			return LocalAction.INPUT;
		}
		return LocalAction.SUCCESS;
	}
	
	public String visualizar() {
		try {
			prepararPaginaDeVisualizacao();
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return LocalAction.SUCCESS;
	}
	
	private void prepararPaginaDeVisualizacao() throws Exception {
		this.local = localDAO.buscar(local.getId());
		carregarAlteracao();
	}
	
	public String prepararPaginaDeAlteracaoDistancia() {
		try {
			this.localComDistancia = locaisComDistanciaDAO.buscar(localComDistancia.getIdLocais());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LocalAction.SUCCESS;
	}
	
	public String editarDistancia(){
		try {
			Double distancia = localComDistancia.getDistancia();
			this.localComDistancia = locaisComDistanciaDAO.buscar(localComDistancia.getIdLocais());
			this.localComDistancia.setDistancia(String.valueOf(distancia));
			
			if(locaisComDistanciaDAO.alterar(localComDistancia) == true) {
				mensagem = "Alteração realizada com sucesso";
			}
			else {
				mensagem = "Falha na alteração";
				return LocalAction.INPUT;
			}

		} catch (Exception e) {
			mensagem = e.getMessage();
			e.printStackTrace();
		}
		
		return LocalAction.SUCCESS;
	}
	
	public String excluirDistancia() {
		try {
			this.localComDistancia = locaisComDistanciaDAO.buscar(localComDistancia.getIdLocais()); 
			this.local = localDAO.buscar(localComDistancia.getLocalDeOrigem().getId());			
			
			if(locaisComDistanciaDAO.remover(localComDistancia) == true) {
				mensagem = "Exclusão realizada com sucesso";
				carregarAlteracao();
			}
			else {
				mensagem = "Falha na exclusão";
				carregarAlteracao();
				return ClienteAction.INPUT;
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
			e.printStackTrace();
		}
		return ClienteAction.SUCCESS;
	}
	
	private boolean camposEmBranco() {
		if(this.local.getNome().isEmpty()) {
			return true;
		}
		return false;
	}
	
	private void carregarAlteracao() throws Exception {
		this.listaDeLocaisSemDistancia = localDAO.Consulta(getQueryDeListarLocaisAposCadastro(local.getId().intValue()));
		String query = "SELECT * FROM locais where id_local_origem = " + local.getId() + " or id_local_destino = " + local.getId();
		this.locais = locaisComDistanciaDAO.Consulta(query);
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
