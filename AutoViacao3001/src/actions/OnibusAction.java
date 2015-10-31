package actions;

import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.OnibusDAO;
import modelo.dao.PoltronaDAO;
import modelo.entidade.Onibus;
import modelo.entidade.Poltrona;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class OnibusAction extends ActionSupport{
	private Onibus onibus = new Onibus();
	private List<Onibus> listaDeOnibus;
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private OnibusDAO oDAO = new OnibusDAO(conexao);
	private PoltronaDAO pDAO = new PoltronaDAO(conexao);
	private String mensagem;
	
	public String adicionar() {
		try {
			Integer idOnibus; 
			if ((idOnibus = oDAO.incluirComRetornoDeId(this.onibus)) != null) {
				mensagem = "Ônibus cadastrado com sucesso.";	
			}
			
			// criação das poltronas do ônibus:
			for (int numero=1; numero <= onibus.getCapacidade(); numero++){
				
				Poltrona poltrona = new Poltrona();
				poltrona.setIdOnibus(idOnibus);
				poltrona.setNumero(numero);
				pDAO.incluir(poltrona);
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
				mensagem = "Preencha os campos obrigatórios";
				return ClienteAction.INPUT;
			}
			else if (oDAO.alterar(this.onibus) == true) {
				mensagem = "Alteração realizada com sucesso";
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
	
	public List<String> getTiposLeito(){
		return MetodosAuxiliares.tiposLeitoOnibus();
	}
}
