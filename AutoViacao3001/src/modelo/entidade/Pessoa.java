package modelo.entidade;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Pessoa {
	protected String cpf;
	protected String nome;
	protected Date dataDeNascimento;
	protected String endereco;
	protected String bairro;
	protected String cep;
	protected String estado;
	protected String telefoneResidencial;
	protected String telefoneCelular;
	
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}
	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public String getDataDeNascimento() {
		return DATETIME_FORMAT.format(dataDeNascimento);
	}
	public void setDataDeNascimento(String dataDeNascimento) throws ParseException {
		Date dataFormatada = DATETIME_FORMAT.parse(dataDeNascimento);
		this.dataDeNascimento = dataFormatada;
	}
}
