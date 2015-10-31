package actions;

import java.util.ArrayList;
import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.LocaisDAO;
import modelo.dao.MotoristaDAO;
import modelo.dao.OnibusDAO;
import modelo.entidade.Locais;
import modelo.entidade.Motorista;
import modelo.entidade.Onibus;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MetodosAuxiliares extends ActionSupport {

	private ConexaoPostgres conexao = new ConexaoPostgres();
	private LocaisDAO lDAO = new LocaisDAO(conexao);
	private MotoristaDAO fDAO = new MotoristaDAO(conexao);
	private OnibusDAO oDAO = new OnibusDAO(conexao);
	private List<Locais> listaDeLocais;
	private List<Motorista> listaDeMotoristas;
	private List<Onibus> listaDeOnibus;

	public String obterListasParaFormularioCadastroViagem() {
		try {
			this.listaDeLocais = lDAO.listar();
			this.listaDeOnibus = oDAO.listar();
			this.listaDeMotoristas = fDAO.listarMotoristas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ViagemAction.SUCCESS;
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
	
	public static List<String> tiposLeitoOnibus(){
		List<String> tiposLeito = new ArrayList<>();
		tiposLeito.add("LEITO");
		tiposLeito.add("SEMI-LEITO");
		tiposLeito.add("EXECUTIVO");
		tiposLeito.add("CONVENCIONAL");
		return tiposLeito;
	}


	public static List<String> estadosBrasileiros() {
		List<String> estados = new ArrayList<String>();
		estados.add("AC");
		estados.add("AL");
		estados.add("AM");
		estados.add("AP");
		estados.add("BA");
		estados.add("CE");
		estados.add("DF");
		estados.add("ES");
		estados.add("GO");
		estados.add("MA");
		estados.add("MG");
		estados.add("MS");
		estados.add("MT");
		estados.add("PA");
		estados.add("PB");
		estados.add("PE");
		estados.add("PI");
		estados.add("PR");
		estados.add("RJ");
		estados.add("RN");
		estados.add("RO");
		estados.add("RR");
		estados.add("RS");
		estados.add("SC");
		estados.add("SE");
		estados.add("SP");
		estados.add("TO");
		return estados;
	}
	
	
}
