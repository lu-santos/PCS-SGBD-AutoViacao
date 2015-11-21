package actions;

import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.OnibusDAO;
import modelo.dao.PoltronaDAO;
import modelo.dao.ViagemDAO;
import modelo.entidade.Onibus;
import modelo.entidade.Poltrona;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class OnibusAction extends ActionSupport{
	private Onibus onibus = new Onibus();
	private List<Onibus> listaDeOnibus;
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private OnibusDAO oDAO = new OnibusDAO(conexao);
	private ViagemDAO vDAO = new ViagemDAO(conexao);
	private PoltronaDAO pDAO = new PoltronaDAO(conexao);
	private String mensagem;
	
	public String adicionar() {
		try {
			Integer idOnibus; 
			if ((idOnibus = oDAO.incluirComRetornoDeId(this.onibus)) != null) {
				mensagem = "�nibus cadastrado com sucesso.";	
			}
			
			// cria��o das poltronas do �nibus:
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
	
	public String excluir() {
		try {
			if (pDAO.removerPoltronasOnibus(onibus.getIdOnibus())){
				
				if(oDAO.remover(onibus)) {
					mensagem = "Exclus�o realizada com sucesso";
					return OnibusAction.SUCCESS;
				}else {
					mensagem = "Falha na exclus�o";
				}
				
			}
			
			else {
				mensagem = "Falha na exclus�o. Verifique se n�o h� viagens cadastradas com esse �nibus.";
			}
			
		} catch (Exception e) {
			mensagem = "Ocorreu o seguinte erro: " + e.getMessage();
			e.printStackTrace();
		}
		
		return OnibusAction.INPUT;
		
	}
	
	public String viagensOnibus(){
		
		try {
			onibus = oDAO.buscar(onibus.getIdOnibus());
			onibus.setViagens(vDAO.listarViagensOnibus(onibus.getIdOnibus()));
			return SUCCESS;
		} catch (Exception e) {
			mensagem = "Falha na busca das viagens do motorista: " + e.getMessage();
			e.printStackTrace();
		}
		
		return INPUT;
		
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
