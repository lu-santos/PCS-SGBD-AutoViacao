package modelo.entidade;

public class Passagem {
	private Integer id;
	private Integer idOnibus;
	private Integer numeroPoltrona;
	private Double preco;
	private Integer idViagem;
	private String cpfCliente;

	public Passagem() {
	}

	public Passagem(Integer idOnibus, Double preco, Integer idViagem,
			String cpfCliente) {
		this.idOnibus = idOnibus;
		this.preco = preco;
		this.idViagem = idViagem;
		this.cpfCliente = cpfCliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdOnibus() {
		return idOnibus;
	}

	public void setIdOnibus(Integer idOnibus) {
		this.idOnibus = idOnibus;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void setPreco(String preco) {
		this.preco = Double.parseDouble(preco);
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

	public Integer getNumeroPoltrona() {
		return numeroPoltrona;
	}

	public void setNumeroPoltrona(Integer numeroPoltrona) {
		this.numeroPoltrona = numeroPoltrona;
	}

}
