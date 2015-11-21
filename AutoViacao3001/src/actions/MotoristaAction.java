package actions;

import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.MotoristaDAO;
import modelo.dao.ViagemDAO;
import modelo.entidade.Motorista;
import modelo.entidade.Viagem;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MotoristaAction extends ActionSupport{
	private Motorista motorista = new Motorista();
	private List<Motorista> motoristas;
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private MotoristaDAO mDAO = new MotoristaDAO(conexao);
	private ViagemDAO vDAO = new ViagemDAO(conexao);
	private Viagem viagem = new Viagem();
	private String mensagem;
	private List<String> estados;
	
	public String adicionar() {
		try {
			if (mDAO.incluir(this.motorista) == true) {
				mensagem = "Motorista cadastrado com sucesso.";	
			}
		} catch (Exception e) {
			if (e.getMessage().contains("duplicate key value violates unique constraint"))
				mensagem = "CPF já cadastrado. Preencha com outro CPF.";
			else
				mensagem = e.getMessage();
			return MotoristaAction.INPUT;
		}
		this.motorista = new Motorista();
		return MotoristaAction.SUCCESS ;
	}
	
	public String editar() {
		try {
			if(camposEmBranco() == true) {
				mensagem = "Preencha os campos obrigatórios";
				return ClienteAction.INPUT;
			}
			else if (mDAO.alterar(this.motorista) == true) {
				mensagem = "Alteração realizada com sucesso";
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		this.motorista = new Motorista();
		return MotoristaAction.SUCCESS;
	}
	
	public String visualizar() {
		try {
			this.motorista = mDAO.buscar(this.motorista.getCpf());
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return MotoristaAction.SUCCESS;
	}
	
	public String listarMotorista() {
		try {
			this.motoristas = mDAO.listar();
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return MotoristaAction.SUCCESS;
	}
	
	public String excluir() {
		try {
			if(mDAO.remover(motorista) == true) {
				mensagem = "Exclusão realizada com sucesso";
			}
			else {
				mensagem = "Falha na exclusão";
				return MotoristaAction.INPUT;
			}
		} catch (Exception e) {
			mensagem = e.getMessage();
			e.printStackTrace();
		}
		return MotoristaAction.SUCCESS;
	}
	
	public String viagensMotorista(){
		
		try {
			motorista = mDAO.buscar(motorista.getCpf());
			motorista.setViagens(vDAO.listarViagensMotorista(motorista.getCpf()));
			return SUCCESS;
		} catch (Exception e) {
			mensagem = "Falha na busca das viagens do motorista: " + e.getMessage();
			e.printStackTrace();
		}
		
		return INPUT;
		
	} 
	
	public String resultadoDaConsultaMotorista() {
		try {
			this.motoristas = mDAO.listarMotoristaComMaisViagem(this.viagem.getDataHoraPartidaString(), this.viagem.getDataHoraChegadaString());
			if (motoristas.size() == 0)
				this.mensagem = "Não foram encontrados resultados para os filtros selecionados.";
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		return ViagemAction.SUCCESS;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista funcionario) {
		this.motorista = funcionario;
	}
	
	public List<String> getEstados() {
		estados = MetodosAuxiliares.estadosBrasileiros();
		return estados;
	}
	
	public void setEstados(List<String> estados) {
		this.estados = estados;
	}
	
	public boolean camposEmBranco() {
		if (motorista.getNome().length() == 0 || motorista.getEndereco().length() == 0 || 
				motorista.getBairro().length() == 0 || motorista.getCep().length() == 0 ||
				motorista.getBairro().length() == 0 || motorista.getCidade().length() == 0 || motorista.getCep().length() == 0 ||
				motorista.getTelefoneResidencial().length() == 0 || 
				motorista.getTelefoneCelular().length() == 0 ||
				motorista.getSalario() == null || motorista.getSalario().isNaN() == true || motorista.getDataDeContratacao().length() == 0) {
			return true;
		}
		return false;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public void setMotoristas(List<Motorista> motoristas) {
		this.motoristas = motoristas;
	}

	public Viagem getViagem() {
		return this.viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}
}
