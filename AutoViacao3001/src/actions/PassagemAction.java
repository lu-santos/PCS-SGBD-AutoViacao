package actions;

import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.OnibusDAO;
import modelo.dao.PassagemDAO;
import modelo.dao.ViagemDAO;
import modelo.entidade.Onibus;
import modelo.entidade.Passagem;
import modelo.entidade.Viagem;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PassagemAction extends ActionSupport {

	private ConexaoPostgres conexao = new ConexaoPostgres();
	private ViagemDAO vDAO = new ViagemDAO(conexao);
	private OnibusDAO oDAO = new OnibusDAO(conexao);
	private PassagemDAO pDAO = new PassagemDAO(conexao);
	private String mensagem;
	private List<Viagem> listaDeViagens;
	private List<Passagem> listaDePassagens;
	private Viagem viagem;
	private Passagem passagem;

	public String listarViagensSemPassagem() {
		try {
			listaDeViagens = vDAO.listarViagensSemPassagem();
			return SUCCESS;
		} catch (Exception e) {
			mensagem = "Falha ao listar viagens sem passagem: "
					+ e.getMessage();
			e.printStackTrace();
		}
		return INPUT;

	}

	public String prepararParaGerar() {
		try {
			viagem = vDAO.buscar(viagem.getIdViagem());
			return SUCCESS;
		} catch (Exception e) {
			mensagem = "Falha ao buscar a viagem: " + e.getMessage();
			e.printStackTrace();
		}
		return INPUT;

	}

	public String gerarPassagens() {
		try {
			Onibus onibus = oDAO.buscar(viagem.getIdOnibus());

			for (int numero = 1; numero <= onibus.getCapacidade(); numero++) {
				Passagem passagem = new Passagem();
				passagem.setIdOnibus(viagem.getIdOnibus());
				passagem.setIdViagem(viagem.getIdViagem());
				passagem.setNumeroPoltrona(numero);
				passagem.setPreco(this.passagem.getPreco());
				pDAO.incluir(passagem);
			}

			mensagem = "Passagens geradas com sucesso.";
			return SUCCESS;

		} catch (Exception e) {
			mensagem = "Falha ao gerar passagens: " + e.getMessage();
			e.printStackTrace();
		}
		return INPUT;
	}

	public String listar() {
		try {
			listaDePassagens = pDAO.listar();
		} catch (Exception e) {
			mensagem = "Falha ao listar passagens: " + e.getMessage();
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<Viagem> getListaDeViagens() {
		return listaDeViagens;
	}

	public void setListaDeViagens(List<Viagem> listaDeViagens) {
		this.listaDeViagens = listaDeViagens;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public Passagem getPassagem() {
		return passagem;
	}

	public void setPassagem(Passagem passagem) {
		this.passagem = passagem;
	}

	public List<Passagem> getListaDePassagens() {
		return listaDePassagens;
	}

	public void setListaDePassagens(List<Passagem> listaDePassagens) {
		this.listaDePassagens = listaDePassagens;
	}

}