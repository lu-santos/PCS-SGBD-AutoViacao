package modelo.entidade;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Motorista extends Pessoa {
	private Double salario;
	private Date dataDeContratacao;
	private List<Viagem> viagens;
	
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public Motorista() {
		
	}
	
	public Motorista(String cpf, String nome, Date dataDeNascimento, String endereco, String bairro, String cep, String estado, String telefoneResidencial, String telefoneCelular, String salario, String dataDeContratacao) throws ParseException {
		this.cpf = cpf;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cep = cep;
		this.estado = estado;
		this.telefoneResidencial = telefoneResidencial;
		this.telefoneCelular = telefoneCelular;
		this.salario = Double.valueOf(salario);
		Date dataFormatada = DATETIME_FORMAT.parse(dataDeContratacao);
		this.dataDeContratacao = dataFormatada;
	}

	public Double getSalario() {
		return this.salario;
	}

	public void setSalario(String salario) {
		Double formatado = Double.valueOf(salario);
		this.salario = formatado;
	}

	public String getDataDeContratacao() {
		return DATETIME_FORMAT.format(dataDeContratacao);
	}

	public void setDataDeContratacao(String dataDeContratacao) throws ParseException {
		Date dataFormatada = DATETIME_FORMAT.parse(dataDeContratacao);
		this.dataDeContratacao = dataFormatada;
	}

	public List<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(List<Viagem> viagens) {
		this.viagens = viagens;
	}
}
