package modelo.entidade;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Viagem {
	private Integer idViagem;
	private Date dataPartida;
	private String horaPartida;
	private Date dataChegada;
	private String horaChegada;
	private Double distancia;
	private Integer idLocalPartida;
	private Integer idLocalDestino;
	private Integer idOnibus;
	private String cpfMotorista;
	private List<Passagem> passagens;
	
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public Viagem() {}
	
	public Viagem(Integer idViagem, Date dataPartida, String horaPartida, Date dataChegada, 
			String horaChegada, Double distancia, Integer idLocalPartida, Integer idLocalDestino,
			Integer idOnibus, String cpfMotorista){
		this.idViagem = idViagem;
		this.dataPartida = dataPartida;
		this.horaPartida = horaPartida;
		this.dataChegada = dataChegada;
		this.horaChegada = horaChegada;
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

	public String getDataPartida() {
		return DATETIME_FORMAT.format(dataPartida);
	}

	public void setDataPartida(String dataPartida) throws ParseException {
		Date dataFormatada = DATETIME_FORMAT.parse(dataPartida);
		this.dataPartida = dataFormatada;
	}

	public String getHoraParttida() {
		return horaPartida;
	}

	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}

	public String getDataChegada() {
		return DATETIME_FORMAT.format(dataChegada);
	}

	public void setDataChegada(String dataChegada) throws ParseException {
		Date dataFormatada = DATETIME_FORMAT.parse(dataChegada);
		this.dataChegada = dataFormatada;
	}

	public String getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(String horaChegada) {
		this.horaChegada = horaChegada;
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
	
}
