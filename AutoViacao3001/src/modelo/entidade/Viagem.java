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
	private Double distancia;
	private Integer idLocalPartida;
	private Integer idLocalDestino;
	private Integer idOnibus;
	private String cpfMotorista;
	private List<Passagem> passagens;
	
//	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
//	private static final DateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm:ss");
	
	public Viagem() {}
	
	public Viagem(Integer idViagem, Date dataHoraPartida, Date dataHoraChegada, Double distancia,
			Integer idLocalPartida, Integer idLocalDestino, Integer idOnibus, String cpfMotorista){
		this.idViagem = idViagem;
		this.dataHoraPartida = dataHoraPartida;
		this.dataHoraPartida = dataHoraChegada;
		this.distancia = distancia;
		this.idLocalPartida = idLocalPartida;
		this.idLocalDestino = idLocalDestino;
		this.idOnibus = idOnibus;
		this.cpfMotorista = cpfMotorista;
	}
	
	public Viagem(Date dataHoraPartida, Date dataHoraChegada, Double distancia,
			Integer idLocalPartida, Integer idLocalDestino, Integer idOnibus, String cpfMotorista){
		this.dataHoraPartida = dataHoraPartida;
		this.dataHoraPartida = dataHoraChegada;
		this.distancia = distancia;
		this.idLocalPartida = idLocalPartida;
		this.idLocalDestino = idLocalDestino;
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

	public void setDataHoraPartida(String dataHoraPartida) throws ParseException {
		dataHoraPartidaString = dataHoraPartida;
		this.dataHoraPartida = DataUtil.converterStringParaDataComHora(dataHoraPartida);
	}

	public Date getDataHoraChegada() {
		return dataHoraChegada;
	}

	public void setDataHoraChegada(String dataHoraChegada) throws ParseException {
		dataHoraChegadaString = dataHoraChegada;
		this.dataHoraChegada = DataUtil.converterStringParaDataComHora(dataHoraChegada);
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Integer getIdLocalPartida() {
		return idLocalPartida;
	}

	public void setIdLocalPartida(Integer idLocalPartida) {
		this.idLocalPartida = idLocalPartida;
	}

	public Integer getIdLocalDestino() {
		return idLocalDestino;
	}

	public void setIdLocalDestino(Integer idLocalDestino) {
		this.idLocalDestino = idLocalDestino;
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

	