package modelo.entidade;

public class Passagem {
	private Integer id;
	private Poltrona poltrona;
//	private Integer idOnibus;
//	private Integer numeroPoltrona;
	private Double preco;
	private Viagem viagem;
	private Cliente cliente;
	private int quantidade;
	private Double total;

	public Passagem() {
	}

	public Passagem(Double preco, Viagem viagem,
			Cliente cliente, Poltrona poltrona) {
		//this.idOnibus = idOnibus;
		this.poltrona = poltrona;
		this.preco = preco;
		this.viagem = viagem;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

/*	public Integer getIdOnibus() {
		return idOnibus;
	}

	public void setIdOnibus(Integer idOnibus) {
		this.idOnibus = idOnibus;
	}
*/
	public Double getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = Double.parseDouble(preco);
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

/*	public Integer getNumeroPoltrona() {
		return numeroPoltrona;
	}

	public void setNumeroPoltrona(Integer numeroPoltrona) {
		this.numeroPoltrona = numeroPoltrona;
	}
*/
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getTotal() {
		return this.total = preco*quantidade;
	}

	public Poltrona getPoltrona() {
		return poltrona;
	}

	public void setPoltrona(Poltrona poltrona) {
		this.poltrona = poltrona;
	}

}
