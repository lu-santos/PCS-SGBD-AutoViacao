package actions;

import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.LocaisDAO;
import modelo.dao.MotoristaDAO;
import modelo.dao.OnibusDAO;
import modelo.dao.PassagemDAO;
import modelo.dao.ViagemDAO;
import modelo.entidade.Locais;
import modelo.entidade.Motorista;
import modelo.entidade.Onibus;
import modelo.entidade.Passagem;
import modelo.entidade.Viagem;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ViagemAction extends ActionSupport {
	private Viagem viagem = new Viagem();
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private ViagemDAO vDAO = new ViagemDAO(conexao);
	private LocaisDAO lDAO = new LocaisDAO(conexao);
	private MotoristaDAO mDAO = new MotoristaDAO(conexao);
	private OnibusDAO oDAO = new OnibusDAO(conexao);
	private PassagemDAO pDAO = new PassagemDAO(conexao);
	private String mensagem;
	private List<Viagem> listaDeViagens;
	private List<Locais> listaDeLocais;
	private List<Motorista> listaDeMotoristas;
	private List<Onibus> listaDeOnibus;
	private Double lucroBruto = 0.0;
	private Double valorDaPassagem;

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
			this.listaDeMotoristas = mDAO.listarMotoristas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ViagemAction.SUCCESS;
	}
	
	public String consultarViagem(){
		obterListasParaFormulario();
		return ViagemAction.SUCCESS;
	}
	
	public String resultadoDaConsultaViagem(){
		try {			
			obterListasParaFormulario();
			
			if (viagem.getLocais() != null && viagem.getLocais().getIdLocais() != null && viagem.getDataHoraPartidaString() != null && viagem.getDataHoraPartidaString().length() != 0)
				this.listaDeViagens = vDAO.ListarResultadoDaConsultaViagens(viagem.getLocais().getIdLocais(), viagem.getDataHoraPartidaString());
			else if (viagem.getLocais() != null && viagem.getLocais().getIdLocais() != null)
				this.listaDeViagens = vDAO.listarResultadoDaConsultaViagensPorLocais(viagem.getLocais().getIdLocais());
			else if (viagem.getDataHoraPartidaString().length() != 0)
				this.listaDeViagens = vDAO.listarResultadoDaConsultaViagemPorData(viagem.getDataHoraPartidaString());
			else
				this.mensagem = "Favor preencher um dos campos e realizar nova busca.";
			
			if (this.listaDeViagens.size() == 0)
				this.mensagem = "Não foram encontrados resultados para os filtros selecionados.";
			
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return ViagemAction.SUCCESS;
	}
	
	public String viagemPassageiros() {
		try {
			viagem = vDAO.buscar(viagem.getIdViagem());
			viagem.setPassageiros(pDAO.listarPassageirosDeUmaViagem(viagem.getIdViagem()));
			return SUCCESS;
		} catch (Exception e) {
			mensagem = "Falha na busca das viagens do motorista: " + e.getMessage();
			e.printStackTrace();
		}
		
		return INPUT;
	}
	
	public String viagemLucroBruto() {
		try {
			viagem = vDAO.buscar(viagem.getIdViagem());
			viagem.setPassageiros(pDAO.listarPassageirosDeUmaViagem(viagem.getIdViagem()));
			lucroBruto = 0.0;
			List<Passagem> passagens = viagem.getPassageiros();
			if(passagens.size() > 0) { 
				for(Passagem passagem : passagens){
					lucroBruto = lucroBruto + passagem.getPreco();
				}
				valorDaPassagem = passagens.get(0).getPreco();
			}
			else {
				String query = "Select * from passagem where id_viagem = " + viagem.getIdViagem();
				if (pDAO.Consulta(query).size() > 0)
					valorDaPassagem = pDAO.Consulta(query).get(0).getPreco();
				else 
					valorDaPassagem = 0.0;
			}
			return SUCCESS;
		} catch (Exception e) {
			mensagem = "Falha na busca das viagens do motorista: " + e.getMessage();
			e.printStackTrace();
		}
		
		return INPUT;
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
		if (viagem.getMotorista().getCpf().length() == 0 || viagem.getDataHoraChegadaFormatoJSP().length() == 0
				|| viagem.getDataHoraPartidaFormatoJSP().length() == 0|| viagem.getLocais().getIdLocais() == null || viagem.getOnibus().getIdOnibus() == null) {
			return true;
		}
		return false;
	}

	public Double getLucroBruto() {
		return lucroBruto;
	}

	public void setLucroBruto(Double lucroBruto) {
		this.lucroBruto = lucroBruto;
	}

	public Double getValorDaPassagem() {
		return valorDaPassagem;
	}

	public void setValorDaPassagem(Double valorDaPassagem) {
		this.valorDaPassagem = valorDaPassagem;
	}


}
