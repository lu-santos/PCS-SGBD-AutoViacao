package actions;

import java.util.ArrayList;
import java.util.Calendar;
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
	private List<Integer> listaDeAno;
	private int ano;

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
			Onibus onibus = oDAO.buscar(viagem.getOnibus().getIdOnibus());

			for (int numero = 1; numero <= onibus.getCapacidade(); numero++) {
				Passagem passagem = new Passagem();
				passagem.setIdOnibus(viagem.getOnibus().getIdOnibus());
				passagem.setViagem(viagem);
				passagem.setNumeroPoltrona(numero);
				passagem.setPreco(String.valueOf(this.passagem.getPreco()));
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
	
	public String consultarEpocaDoAnoComMaisVendas() {
		listarAnoDeViagensComPassagem();
		return SUCCESS;
	}
	
	private void listarAnoDeViagensComPassagem() {
		String query = "select distinct v.id_viagem, data_hora_partida, data_hora_chegada, v.id_onibus, cpf_motorista, id_locais "
				+ "from passagem p join viagem v on p.id_viagem = v.id_viagem "
				+ "where p.cpf_cliente != ''";
		try {
			listaDeViagens = vDAO.Consulta(query);
			listaDeAno = new ArrayList<>();
			for(Viagem viagem : listaDeViagens) {
				Calendar ano = Calendar.getInstance();
				ano.setTime(viagem.getDataHoraPartida());
				listaDeAno.add(ano.get(Calendar.YEAR));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String listarEpocaDoAnoComMaisVendas() {
		String query = "select p.id_viagem, preco, count(p.id_viagem) as total_de_passagens "
						+ "from passagem p join viagem v on p.id_viagem = v.id_viagem " 
						+ "where p.cpf_cliente != '' and extract (year from v.data_hora_partida) = " 
						+ ano
						+ " group by p.id_viagem, p.preco order by total_de_passagens desc";
		
		try {
			this.listaDePassagens = pDAO.PassagensMaisVendidadas(query);
			listarAnoDeViagensComPassagem();
			if(this.listaDePassagens.size() == 0)
				this.mensagem = "Não foram encontrados resultados para os filtros selecionados.";
		} catch (Exception e) {
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

	public List<Integer> getListaDeAno() {
		return listaDeAno;
	}

	public void setListaDeAno(List<Integer> listaDeAno) {
		this.listaDeAno = listaDeAno;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}