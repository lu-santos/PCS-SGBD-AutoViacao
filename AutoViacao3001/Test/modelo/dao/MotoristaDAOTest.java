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
		
		Motorista motoristaEsperado = motoristaDAO.buscar(motorista.getCpf());
		
		assertEquals(motoristaEsperado.getClass(), motorista.getClass());
		assertEquals(motoristaEsperado.getCpf(), motorista.getCpf());
		assertEquals(motoristaEsperado.getNome(), motorista.getNome());
		assertEquals(motoristaEsperado.getCep(), motorista.getCep());
	
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
		
		assertEquals("Alfredo de Faria Gomes", motoristaDAO.buscar(cpf).getNome());
		assertEquals("1962-02-27", motoristaDAO.buscar(cpf).getDataDeNascimento());
		assertEquals("Rua Lauro Muller", motoristaDAO.buscar(cpf).getEndereco());
		assertEquals("22290160", motoristaDAO.buscar(cpf).getCep());
		assertEquals("25429090", motoristaDAO.buscar(cpf).getTelefoneResidencial());
		assertEquals("99809765", motoristaDAO.buscar(cpf).getTelefoneCelular());
		assertEquals(0.1, 4500.0, motoristaDAO.buscar(cpf).getSalario());
		assertEquals("2005-04-27", motoristaDAO.buscar(cpf).getDataDeContratacao());	
	}
	
	@Test
	public void dadoUmaDataPartidaEUmaDataDestinoConsultaMotoristaComMaisViagens() throws Exception{
		Motorista motorista = new Motorista();
		listaMotoristas = motoristaDAO.listarMotoristaComMaisViagem("20160104 10:00:00", "20160104 12:30:00");
		
		motorista.setCpf("70527752398");
		assertEquals(listaMotoristas.get(0).getCpf(), motorista.getCpf());
	
		listaMotoristas = motoristaDAO.listarMotoristaComMaisViagem("20151221 08:00:00", "20151221 14:00:00");
		motorista.setCpf("34638754627");
		assertEquals(listaMotoristas.get(0).getCpf(), motorista.getCpf());
	
	}
}
