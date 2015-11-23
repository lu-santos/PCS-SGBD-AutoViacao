package modelo.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import modelo.entidade.Cliente;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClienteDAOTest {

	private ConexaoDAO conexao = new ConexaoPostgres();
	private ClienteDAO clienteDAO = new ClienteDAO(conexao);
	private String cpf = "98712365400";
	private Cliente cliente;
	
	@SuppressWarnings("deprecation")
	private final Date dataNascimento = new Date(62, 4, 26);
	
	@Before
	public void setUp() throws ParseException{
		cliente =  new Cliente(cpf, "Antonio Numes", dataNascimento, "Rua Alice", "Laranjeiras", 
				"Rio de Janeiro", "22241020", "RJ", "25428090", "999989761", "123qwx");
	}
	
	@After
	public void removeMotorista() throws Exception{
		clienteDAO.remover(cliente);
	}
	
	
	
	@Test
	public void dadoUmaListaComCincoClientesCadastradosNoBDVerificaSeExistemCincoClientes() throws Exception {
		List<Cliente> listaClientes = clienteDAO.listar();
		
		assertEquals(5, listaClientes.size());
	}
	
	@Test
	public void dadoUmClienteCadastraNoBD() throws Exception{
		clienteDAO.incluir(cliente);
		
		Cliente clienteObtido = clienteDAO.buscar(cliente.getCpf());
		
		assertEquals(cpf, clienteObtido.getCpf());	
		assertEquals("Antonio Numes", clienteObtido.getNome());	
		assertEquals("1962-05-26", clienteObtido.getDataDeNascimento());	
		assertEquals("Rua Alice", clienteObtido.getEndereco());	
		assertEquals("Laranjeiras", clienteObtido.getBairro());	
		assertEquals("Rio de Janeiro", clienteObtido.getCidade());	
		assertEquals("22241020", clienteObtido.getCep());	
		assertEquals("RJ", clienteObtido.getEstado());	
		assertEquals("25428090", clienteObtido.getTelefoneResidencial());	
		assertEquals("999989761", clienteObtido.getTelefoneCelular());	
		assertEquals("123qwx", clienteObtido.getSenha());	
		
		
	}
	
	@Test
	public void dadoUmClienteCadastraNoBDAlteraESalvaNoBD() throws Exception{
		clienteDAO.incluir(cliente);
		
		Cliente clienteObtido = clienteDAO.buscar(cliente.getCpf());
		
		assertEquals(cpf, clienteObtido.getCpf());	
		assertEquals("Antonio Numes", clienteObtido.getNome());	
		assertEquals("1962-05-26", clienteObtido.getDataDeNascimento());	
		assertEquals("Rua Alice", clienteObtido.getEndereco());	
		assertEquals("Laranjeiras", clienteObtido.getBairro());	
		assertEquals("Rio de Janeiro", clienteObtido.getCidade());	
		assertEquals("22241020", clienteObtido.getCep());	
		assertEquals("RJ", clienteObtido.getEstado());	
		assertEquals("25428090", clienteObtido.getTelefoneResidencial());	
		assertEquals("999989761", clienteObtido.getTelefoneCelular());	
		assertEquals("123qwx", clienteObtido.getSenha());	
		
		cliente.setNome("Antonio Eduardo Numes");
		cliente.setEndereco("Rua Alice, 23");
		cliente.setSenha("wasd123");
		clienteDAO.alterar(cliente);
		
		Cliente clienteObtido2 = clienteDAO.buscar(cpf);
		assertEquals(cpf, clienteObtido2.getCpf());
		assertEquals("Antonio Eduardo Numes", clienteObtido2.getNome());
		assertEquals("Rua Alice, 23", clienteObtido2.getEndereco());
		assertEquals("wasd123", clienteObtido2.getSenha());
	}
}
