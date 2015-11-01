package actions;

import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.LocaisDAO;
import modelo.dao.MotoristaDAO;
import modelo.dao.OnibusDAO;
import modelo.dao.ViagemDAO;
import modelo.entidade.Locais;
import modelo.entidade.Motorista;
import modelo.entidade.Onibus;
import modelo.entidade.Viagem;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ViagemAction extends ActionSupport {
	private Viagem viagem = new Viagem();
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private ViagemDAO vDAO = new ViagemDAO(conexao);
	private LocaisDAO lDAO = new LocaisDAO(conexao);
	private MotoristaDAO fDAO = new MotoristaDAO(conexao);
	private OnibusDAO oDAO = new OnibusDAO(conexao);
	private String mensagem;
	private List<Viagem> listaDeViagens;
	private List<Locais> listaDeLocais;
	private List<Motorista> listaDeMotoristas;
	private List<Onibus> listaDeOnibus;

	public String adicionar() {
		try {
			if (vDAO.existeConflitoDeOnibus(this.viagem)){
				mensagem = "Viagem não cadastrada: o ônibus já foi selecionado para uma outra viagem, cujo horário é conflitante com o da viagem atual.";
				return ViagemAction.INPUT;
			}
			
			if (vDAO.existeConflitoDeMotorista(this.viagem)){
				mensagem = "Viagem não cadastrada: o motorista já está escalado para uma outra viagem, cujo horário é conflitante com o da viagem atual.";
				return ViagemAction.INPUT;
			}
			
			if (vDAO.incluir(this.viagem) == true) {
				mensagem = "Viagem cadastrada com sucesso.";
			}
		} catch (Exception e) {
			mensagem = "Falha ao adicionar a viagem: " + e.getMessage();
			e.printStackTrace();
			return ViagemAction.INPUT;
		}
		this.viagem = new Viagem();
		return ViagemAction.SUCCESS;
	}

	public String listar() {
		try {
			this.listaDeViagens = vDAO.listar();
		} catch (Exception e) {
			mensagem = "Falha ao listar viagens: " + e.getMessage();
		}
		return ViagemAction.SUCCESS;
	}
	
	public String visualizar() {
		try {
			this.viagem = vDAO.buscar(this.viagem.getIdViagem());
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return ViagemAction.SUCCESS;
	}
	
	public String prepararAlteracao() {
		obterListasParaFormulario();
		try {			
			this.viagem = vDAO.buscar(this.viagem.getIdViagem());
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return ViagemAction.SUCCESS;
	}
	
	public String editar() {
		try {
			if(camposEmBranco() == true) {
				mensagem = "Não foram preenchidos todos os campos obrigatórios";
				return ClienteAction.INPUT;
			}
			
			if (vDAO.existeConflitoDeOnibusNaAtualizacao(this.viagem)){
				mensagem = "Viagem não alterada: o ônibus já foi selecionado para uma outra viagem, cujo horário é conflitante com o da viagem atual.";
				return ViagemAction.INPUT;
			}
			
			if (vDAO.existeConflitoDeMotoristaNaAtualizacao(this.viagem)){
				mensagem = "Viagem não alterada: o motorista já está escalado para uma outra viagem, cujo horário é conflitante com o da viagem atual.";
				return ViagemAction.INPUT;
			}
			
			else if (vDAO.alterar(this.viagem) == true) {
				mensagem = "Alteração realizada com sucesso";
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		this.viagem = new Viagem();
		return MotoristaAction.SUCCESS;
	}
	
	public String excluir() {
		try {
			if(vDAO.remover(viagem)) {
				mensagem = "Exclusão realizada com sucesso";
			}
			else {
				mensagem = "Falha na exclusão";
				return ViagemAction.INPUT;
			}
		} catch (Exception e) {
			mensagem = "Ocorreu o seguinte erro: " + e.getMessage();
			e.printStackTrace();
		}
		return ViagemAction.SUCCESS;
	}

	public String obterListasParaFormulario() {
		try {
			this.listaDeLocais = lDAO.listar();
			this.listaDeOnibus = oDAO.listar();
			this.listaDeMotoristas = fDAO.listarMotoristas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ViagemAction.SUCCESS;
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

	public List<Locais> getListaDeLocais() {
		return listaDeLocais;
	}

	public void setListaDeLocais(List<Locais> listaDeLocais) {
		this.listaDeLocais = listaDeLocais;
	}

	public List<Motorista> getListaDeMotoristas() {
		return listaDeMotoristas;
	}

	public void setListaDeMotoristas(List<Motorista> listaDeMotoristas) {
		this.listaDeMotoristas = listaDeMotoristas;
	}

	public List<Onibus> getListaDeOnibus() {
		return listaDeOnibus;
	}

	public void setListaDeOnibus(List<Onibus> listaDeOnibus) {
		this.listaDeOnibus = listaDeOnibus;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}
	
	public boolean camposEmBranco() {
		if (viagem.getCpf().length() == 0 || viagem.getDataHoraChegadaFormatoJSP().length() == 0
				|| viagem.getDataHoraPartidaFormatoJSP().length() == 0|| viagem.getIdLocais() == null || viagem.getIdOnibus() == null) {
			return true;
		}
		return false;
	}


}
