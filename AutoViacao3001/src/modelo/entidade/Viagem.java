package modelo.entidade;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import modelo.dao.ConexaoPostgres;
import modelo.dao.LocaisDAO;
import modelo.dao.MotoristaDAO;
import modelo.dao.OnibusDAO;
import util.DataUtil;

public class Viagem {
	private Integer idViagem;
	private Date dataHoraPartida;
	private String dataHoraPartidaString;
	private String dataHoraPartidaFormatada;
	private String dataHoraPartidaFormatoJSP;
	private Date dataHoraChegada;
	private String dataHoraChegadaString;
	private String dataHoraChegadaFormatada;
	private String dataHoraChegadaFormatoJSP;
	private Integer idOnibus;
	private String cpf;
	private Integer idLocais;
	private List<Passagem> passagens;
	private String labelLocais;
	private String labelOnibus;
	private String nomeMotorista;
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private LocaisDAO lDAO = new LocaisDAO(conexao);
	private OnibusDAO oDAO = new OnibusDAO(conexao);
	private MotoristaDAO mDAO = new MotoristaDAO(conexao);

	// private static final DateFormat DATETIME_FORMAT = new
	// SimpleDateFormat("yyyy-MM-dd");
	// private static final DateFormat TIME_FORMAT = new
	// SimpleDateFormat("hh:mm:ss");

	public Viagem() {
	}

	public Viagem(Integer idViagem, Date dataHoraPartida, Date dataHoraChegada,
			Integer idLocais, Integer idOnibus, String cpfMotorista) {
		this.idViagem = idViagem;
		this.dataHoraPartida = dataHoraPartida;
		this.dataHoraPartida = dataHoraChegada;
		this.idLocais = idLocais;
		this.idOnibus = idOnibus;
		this.cpf = cpfMotorista;
	}

	public Viagem(Date dataHoraPartida, Date dataHoraChegada, Integer idLocais,
			Integer idOnibus, String cpfMotorista) {
		this.dataHoraPartida = dataHoraPartida;
		this.dataHoraPartida = dataHoraChegada;
		this.idLocais = idLocais;
		this.idOnibus = idOnibus;
		this.cpf = cpfMotorista;
	}

	public Integer getIdViagem() {
		return idViagem;
	}

	public void setIdViagem(Integer idViagem) {
		this.idViagem = idViagem;
	}

	public Date getDataHoraPartida() {
		return dataHoraPartida;
	}

	public void setDataHoraPartida(String dataHoraPartida)
			throws ParseException {
		dataHoraPartidaString = dataHoraPartida;
		this.dataHoraPartida = DataUtil
				.converterStringParaDataComHora(dataHoraPartida);
	}

	public Date getDataHoraChegada() {
		return dataHoraChegada;
	}

	public void setDataHoraChegada(String dataHoraChegada)
			throws ParseException {
		dataHoraChegadaString = dataHoraChegada;
		this.dataHoraChegada = DataUtil
				.converterStringParaDataComHora(dataHoraChegada);
	}

	public Integer getIdOnibus() {
		return idOnibus;
	}

	public void setIdOnibus(Integer idOnibus) {
		this.idOnibus = idOnibus;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdLocais() {
		return idLocais;
	}

	public void setIdLocais(Integer idLocais) {
		this.idLocais = idLocais;
	}

	public List<Passagem> getPassagens() {
		return passagens;
	}

	public void setPassagens(List<Passagem> passagens) {
		this.passagens = passagens;
	}

	public String getDataHoraPartidaString() {
		dataHoraPartidaString = DataUtil.converterDataComHoraParaString(dataHoraPartida);
		return dataHoraPartidaString;
	}

	public String getDataHoraChegadaString() {
		dataHoraChegadaString =  DataUtil.converterDataComHoraParaString(dataHoraChegada);
		return dataHoraChegadaString;
	}

	public String getDataHoraPartidaFormatada() {
		dataHoraPartidaFormatada = DataUtil.formatarDataHora(dataHoraPartida);
		return dataHoraPartidaFormatada;
	}

	public String getDataHoraChegadaFormatada() {
		dataHoraChegadaFormatada = DataUtil.formatarDataHora(dataHoraChegada);
		return dataHoraChegadaFormatada;
	}

	public String getLabelLocais() {
		try {
			Locais locais = lDAO.buscar(idLocais);
			this.labelLocais = locais.getLabel();
		} catch (Exception e) {
			this.labelLocais = "";
			e.printStackTrace();
		}
		return labelLocais;
	}

	public String getLabelOnibus() {
		try {
			Onibus onibus = oDAO.buscar(idOnibus);
			labelOnibus = onibus.getLabel();
		} catch (Exception e) {
			labelOnibus = "";
			e.printStackTrace();
		}
		return labelOnibus;
	}

	public String getNomeMotorista() {
		try {
			Motorista motorista = mDAO.buscar(cpf);
			nomeMotorista = motorista.getNome();
		} catch (Exception e) {
			nomeMotorista = "";
			e.printStackTrace();
		}
		return nomeMotorista;
	}

	public String getDataHoraPartidaFormatoJSP() {
		dataHoraPartidaFormatoJSP = DataUtil.converterDataComHoraParaFormatoJSP(dataHoraPartida);
		return dataHoraPartidaFormatoJSP;
	}

	public void setDataHoraPartidaFormatoJSP(String dataHoraPartidaFormatoJSP) {
		try {
			dataHoraPartida = DataUtil.converterStringParaDataComHora(dataHoraPartidaFormatoJSP);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.dataHoraPartidaFormatoJSP = dataHoraPartidaFormatoJSP;
	}

	public String getDataHoraChegadaFormatoJSP() {
		dataHoraChegadaFormatoJSP = DataUtil.converterDataComHoraParaFormatoJSP(dataHoraChegada);
		return dataHoraChegadaFormatoJSP;
	}

	public void setDataHoraChegadaFormatoJSP(String dataHoraChegadaFormatoJSP) {
		try {
			dataHoraChegada = DataUtil.converterStringParaDataComHora(dataHoraChegadaFormatoJSP);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.dataHoraChegadaFormatoJSP = dataHoraChegadaFormatoJSP;
	}


}
