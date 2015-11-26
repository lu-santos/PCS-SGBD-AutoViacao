package modelo.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelo.entidade.Motorista;

public class MotoristaDAOTest {
	private ConexaoDAO conexao = new ConexaoPostgres();
	private MotoristaDAO motoristaDAO = new MotoristaDAO(conexao);
	private List<Motorista> listaMotoristas;
	private String cpf = "96078935021";
	@SuppressWarnings("deprecation")
	private final Date dataNascimento = new Date(62, 2, 26);
	private Motorista motorista;
	
	@Before
	public void setUp() throws ParseException{
		motorista = new Motorista(cpf, "Alfredo Gomes Faria", dataNascimento, "Rua da Passagem", "Botafogo", 
				"Rio de Janeiro", "22290030", "RJ", "25428090", "999989761", "4000", "2005-02-27");
	}
	
	@After
	public void removeMotorista() throws Exception{
		motoristaDAO.remover(motorista);
	}
	
	@Test
	public void dadoTresMotoristasCadastradosNoBDVerificaSeTotalDeMotoristasIgualATres() throws Exception {
		listaMotoristas = motoristaDAO.listar();
		assertEquals(0.1, 3, listaMotoristas.size());
	}

	@Test
	public void dadoUmMotoristaVerificaInclusaoBuscaERemocaoDoMotorista() throws Exception{
		motoristaDAO.incluir(motorista);
		listaMotoristas = motoristaDAO.listarMotoristas();
		
		assertEquals(4, listaMotoristas.size());
		
		Motorista motoristaObtido = motoristaDAO.buscar(motorista.getCpf());
		
		assertEquals(cpf, motoristaObtido.getCpf());
		assertEquals("Alfredo Gomes Faria", motoristaObtido.getNome());
		assertEquals("22290030", motoristaObtido.getCep());
	
	}
	
	@Test
	public void dadoUmaMotoristaVerificaAlterarDadosCadastrais() throws Exception{
		motoristaDAO.incluir(motorista);
		
		//motorista.setCpf(cpf);
		motorista.setNome("Alfredo de Faria Gomes");
		motorista.setDataDeNascimento("1962-02-27");
		motorista.setEndereco("Rua Lauro Muller");
		motorista.setBairro("Urca");
		//motorista.setCidade("Rio de Janeiro");
		motorista.setCep("22290160");
		//motorista.setEstado("RJ");
		motorista.setTelefoneResidencial("25429090");
		motorista.setTelefoneCelular("99809765");
		motorista.setSalario("4500");
		motorista.setDataDeContratacao("2005-04-27");
		
		motoristaDAO.alterar(motorista);
		
		Motorista motoristaObtido =  motoristaDAO.buscar(cpf);
		assertEquals("Alfredo de Faria Gomes", motoristaObtido.getNome());
		assertEquals("1962-02-27", motoristaObtido.getDataDeNascimento());
		assertEquals("Rua Lauro Muller", motoristaObtido.getEndereco());
		assertEquals("22290160", motoristaObtido.getCep());
		assertEquals("25429090", motoristaObtido.getTelefoneResidencial());
		assertEquals("99809765", motoristaObtido.getTelefoneCelular());
		assertEquals(0.1, 4500.0, motoristaObtido.getSalario());
		assertEquals("2005-04-27",motoristaObtido.getDataDeContratacao());	
	}
	
	@Test
	public void dadoUmaDataPartidaEUmaDataDestinoConsultaMotoristaComMaisViagens() throws Exception{
		Motorista motorista = new Motorista();
	
		listaMotoristas = motoristaDAO.listarMotoristaComMaisViagem("20151221 08:00:00", "20160101 14:00:00");
		motorista.setCpf("88");
		assertEquals(listaMotoristas.get(0).getCpf(), motorista.getCpf());
	
	}
}
