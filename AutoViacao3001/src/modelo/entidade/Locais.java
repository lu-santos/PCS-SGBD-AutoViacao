package modelo.entidade;

public class Locais {
	private Integer idLocais;
	private Local localDeOrigem;
	private Local localDeDestino;
	private Double distancia;
	private String label;
	
	public Integer getIdLocais() {
		return idLocais;
	}

	public void setIdLocais(Integer idLocais) {
		this.idLocais = idLocais;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		Double formatado = Double.valueOf(distancia);
		this.distancia = formatado;
	}

	public String getLabel() {
		this.label = localDeOrigem.getNome() + " / " + localDeDestino.getNome() + " / " + distancia;
		return label;
	}

	public Local getLocalDeOrigem() {
		return localDeOrigem;
	}

	public void setLocalDeOrigem(Local localDeOrigem) {
		this.localDeOrigem = localDeOrigem;
	}

	public Local getLocalDeDestino() {
		return localDeDestino;
	}

	public void setLocalDeDestino(Local localDeDestino) {
		this.localDeDestino = localDeDestino;
	}
	
}
