package modelo.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Pessoa{
	private String senha;
	private List<Passagem> passagens;
	
	public Cliente() {
		this.passagens = new ArrayList<>();
	}
	
	public Cliente(String cpf, String nome, Date dataDeNascimento, String endereco, String cidade, String bairro, String cep, String estado, String telefoneResidencial, String telefoneCelular, String senha) {
		this.cpf = cpf;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.endereco = endereco;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.estado = estado;
		this.telefoneResidencial = telefoneResidencial;
		this.telefoneCelular = telefoneCelular;
		this.senha = senha;
	}
	
	public Cliente(String cpf, String senha) {
		this.cpf = cpf;
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Passagem> getPassagens() {
		return passagens;
	}

	public void setPassagens(List<Passagem> passagens) {
		this.passagens = passagens;
	}

	
}
