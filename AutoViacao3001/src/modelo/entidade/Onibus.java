package modelo.entidade;

import java.util.List;

public class Onibus {
	private Integer idOnibus;
	private String placa;
	private String modelo;
	private String fabricante;
	private Integer ano;
	private Integer capacidade;
	private String tipoLeito;
	private boolean arCondicionado;
	private boolean banheiro;
	private boolean frigobar;
	private boolean dvd;
	// private List<Poltrona> poltronas;
	private List<Viagem> viagens;
	private String label;

	public Onibus() {
	}

	public Onibus(Integer idOnibus, String placa, String modelo,
			String fabricante, Integer ano, Integer capacidade,
			boolean ar_condicionado, boolean banheiro, boolean frigobar,
			boolean dvd) {
		this.idOnibus = idOnibus;
		this.placa = placa;
		this.modelo = modelo;
		this.fabricante = fabricante;
		this.ano = ano;
		this.capacidade = capacidade;
		this.arCondicionado = ar_condicionado;
		this.banheiro = banheiro;
		this.frigobar = frigobar;
		this.dvd = dvd;
	}

	public Integer getIdOnibus() {
		return idOnibus;
	}

	public void setIdOnibus(Integer idOnibus) {
		this.idOnibus = idOnibus;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public String getTipoLeito() {
		return tipoLeito;
	}

	public void setTipoLeito(String tipoLeito) {
		this.tipoLeito = tipoLeito;
	}

	public boolean isArCondicionado() {
		return arCondicionado;
	}

	public void setArCondicionado(boolean ar_condicionado) {
		this.arCondicionado = ar_condicionado;
	}

	public boolean isBanheiro() {
		return banheiro;
	}

	public void setBanheiro(boolean banheiro) {
		this.banheiro = banheiro;
	}

	public boolean isFrigobar() {
		return frigobar;
	}

	public void setFrigobar(boolean frigobar) {
		this.frigobar = frigobar;
	}

	public boolean isDvd() {
		return dvd;
	}

	public void setDvd(boolean dvd) {
		this.dvd = dvd;
	}

	public List<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(List<Viagem> viagens) {
		this.viagens = viagens;
	}

	public String getLabel() {
		label = modelo + " - " + placa;
		return label;
	}
	
	

}
