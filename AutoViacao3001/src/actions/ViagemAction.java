package actions;

import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.FuncionarioDAO;
import modelo.dao.LocalDAO;
import modelo.dao.OnibusDAO;
import modelo.dao.ViagemDAO;
import modelo.entidade.Funcionario;
import modelo.entidade.Local;
import modelo.entidade.Onibus;
import modelo.entidade.Viagem;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ViagemAction extends ActionSupport {
	private Viagem viagem = new Viagem();
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private ViagemDAO vDAO = new ViagemDAO(conexao);
	private LocalDAO lDAO = new LocalDAO(conexao);
	private FuncionarioDAO fDAO = new FuncionarioDAO(conexao);
	private OnibusDAO oDAO = new OnibusDAO(conexao);
	private String mensagem;
	private List<Viagem> listaDeViagens;
	private List<Local> listaDeLocais;
	private List<Funcionario> listaDeMotoristas;
	private List<Onibus> listaDeOnibus;

	public String adicionar() {
		try {
			if (vDAO.existeConflitoDeOnibus(this.viagem)){
				mensagem = "Viagem não cadastrada: o ônibus já foi selecionado para uma outra viagem, cujo horário é conflitante com o da viagem atual.";
				return FuncionarioAction.INPUT;
			}
			
			if (vDAO.existeConflitoDeMotorista(this.viagem)){
				mensagem = "Viagem não cadastrada: o motorista já está escalado para uma outra viagem, cujo horário é conflitante com o da viagem atual.";
				return FuncionarioAction.INPUT;
			}
			
			if (vDAO.incluir(this.viagem) == true) {
				mensagem = "Viagem cadastrada com sucesso.";
			}
		} catch (Exception e) {
			mensagem = "Falha ao adicionar a viagem: " + e.getMessage();
			e.printStackTrace();
			return FuncionarioAction.INPUT;
		}
		this.viagem = new Viagem();
		return FuncionarioAction.SUCCESS;
	}

	public String listar() {
		try {
			this.listaDeViagens = vDAO.listar();
		} catch (Exception e) {
			mensagem = "Falha ao listar viagens: " + e.getMessage();
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

	public List<Local> getListaDeLocais() {
		return listaDeLocais;
	}

	public void setListaDeLocais(List<Local> listaDeLocais) {
		this.listaDeLocais = listaDeLocais;
	}

	public List<Funcionario> getListaDeMotoristas() {
		return listaDeMotoristas;
	}

	public void setListaDeMotoristas(List<Funcionario> listaDeMotoristas) {
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


}
