package modelo.entidade;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import util.DataUtil;

public class Viagem {
	private Integer idViagem;
	private Date dataHoraPartida;
	private String dataHoraPartidaString;
	private Date dataHoraChegada;
	private String dataHoraChegadaString;
	private Integer idOnibus;
	private String cpfMotorista;
	private Integer idLocais;
	private List<Passagem> passagens;

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
		this.cpfMotorista = cpfMotorista;
	}

	public Viagem(Date dataHoraPartida, Date dataHoraChegada, Integer idLocais,
			Integer idOnibus, String cpfMotorista) {
		this.dataHoraPartida = dataHoraPartida;
		this.dataHoraPartida = dataHoraChegada;
		this.idLocais = idLocais;
		this.idOnibus = idOnibus;
		this.cpfMotorista = cpfMotorista;
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

	public String getCpfMotorista() {
		return cpfMotorista;
	}

	public void setCpfMotorista(String cpfMotorista) {
		this.cpfMotorista = cpfMotorista;
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
		return dataHoraPartidaString;
	}

	public String getDataHoraChegadaString() {
		return dataHoraChegadaString;
	}

}
