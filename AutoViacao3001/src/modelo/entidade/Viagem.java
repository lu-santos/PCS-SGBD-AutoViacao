package modelo.entidade;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
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
	//private List<Passagem> passagens;
	private Onibus onibus;
	private Motorista motorista;
	private Locais locais;
	private List<Passagem> passageiros;
	
	// private static final DateFormat DATETIME_FORMAT = new
	// SimpleDateFormat("yyyy-MM-dd");
	// private static final DateFormat TIME_FORMAT = new
	// SimpleDateFormat("hh:mm:ss");
	
	public Viagem() {
	}

	public Viagem(Integer idViagem, Date dataHoraPartida, Date dataHoraChegada,
			Locais locais, Onibus onibus, Motorista motorista) {
		this.idViagem = idViagem;
		this.dataHoraPartida = dataHoraPartida;
		this.dataHoraPartida = dataHoraChegada;
		this.locais = locais;
		this.onibus = onibus;
		this.motorista = motorista;
	}

	public Viagem(Date dataHoraPartida, Date dataHoraChegada, Locais locais, 
			Onibus onibus, Motorista motorista) {
		this.dataHoraPartida = dataHoraPartida;
		this.dataHoraPartida = dataHoraChegada;
		this.locais = locais;
		this.onibus = onibus;
		this.motorista = motorista;
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

	public String getDataHoraPartidaFormatoJSP() {
		dataHoraPartidaFormatoJSP = DataUtil.converterDataComHoraParaFormatoJSP(dataHoraPartida);
		return dataHoraPartidaFormatoJSP;
	}

	public void setDataHoraPartidaFormatoJSP(String dataHoraPartidaFormatoJSP) {
		try {
			if (dataHoraPartidaFormatoJSP.length() > 0)
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

	public Onibus getOnibus() {
		return onibus;
	}

	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Locais getLocais() {
		return locais;
	}

	public void setLocais(Locais locais) {
		this.locais = locais;
	}

	public List<Passagem> getPassageiros() {
		return passageiros;
	}

	public void setPassageiros(List<Passagem> passageiros) {
		this.passageiros = passageiros;
	}


}
