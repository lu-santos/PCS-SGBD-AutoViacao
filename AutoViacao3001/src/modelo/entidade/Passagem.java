package modelo.entidade;

public class Passagem {
	private Integer idPassagem;
	private Integer numeroPoltrona;
	private Double preco;
	private Integer idViagem;
	private String cpfCliente;
	
	public Passagem() {}
	
	public Passagem(Integer idPassagem, Integer numeroPoltrona, Double preco, Integer idViagem, String cpfCliente) {
		this.idPassagem = idPassagem;
		this.numeroPoltrona = numeroPoltrona;
		this.preco = preco;
		this.idViagem = idViagem;
		this.cpfCliente = cpfCliente;
	}

	public Integer getIdPassagem() {
		return idPassagem;
	}

	public void setIdPassagem(Integer idPassagem) {
		this.idPassagem = idPassagem;
	}

	public Integer getNumeroPoltrona() {
		return numeroPoltrona;
	}

	public void setNumeroPoltrona(Integer numeroPoltrona) {
		this.numeroPoltrona = numeroPoltrona;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getIdViagem() {
		return idViagem;
	}

	public void setIdViagem(Integer idViagem) {
		this.idViagem = idViagem;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
}
