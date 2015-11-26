package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Cliente;
import modelo.entidade.Passagem;
import modelo.entidade.Poltrona;

public class PassagemDAO extends BaseCrudDAO<Passagem>{
	
	private final String tabelaPassagem = "passagem";
	private ClienteDAO clienteDAO;
	private ViagemDAO viagemDAO;

	public PassagemDAO(ConexaoDAO conexao) {
		super(conexao);
	}
	
	public PassagemDAO() {}
	
	@Override
	public String getQueryDeExiste(Passagem entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaPassagem + " (id_viagem, id_onibus, numero_poltrona, preco) VALUES(?, ?, ?, ?)";
	}

	@Override
	public String getQueryDeAlteracao(Passagem entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeRemocao(Passagem entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT * FROM " + tabelaPassagem;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Passagem getEntidade(ResultSet registro) {
		Passagem passagem;
        try {
        	passagem = new Passagem();
        	passagem.setId(registro.getInt("id"));
        	Poltrona poltrona = new Poltrona();
        	poltrona.setIdOnibus(registro.getInt("id_onibus"));
        	poltrona.setNumero(registro.getInt("numero_poltrona"));
        	passagem.setPoltrona(poltrona);
        	passagem.setPreco(String.valueOf(registro.getDouble("preco")));
        	viagemDAO = new ViagemDAO(conexao);
        	passagem.setViagem(viagemDAO.buscar(registro.getInt("id_viagem")));
        	if (registro.getString("cpf_cliente") == null || registro.getString("cpf_cliente").trim().isEmpty()) {
        		passagem.setCliente(new Cliente());
        	}
        	else {
        		clienteDAO = new ClienteDAO(conexao);
        		String cpf = registro.getString("cpf_cliente").trim();
        		passagem.setCliente(clienteDAO.buscar(cpf));
        	}
        	return passagem;
        } catch (Exception ex) {
        	System.out.println("Erro ao pegar passagem no banco - " + ex);
            Logger.getLogger(PassagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Passagem passagem) {
		try {
			pst.setInt(1, passagem.getViagem().getIdViagem());
			pst.setInt(2, passagem.getPoltrona().getIdOnibus());
			pst.setInt(3, passagem.getPoltrona().getNumero());
			pst.setDouble(4, passagem.getPreco());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Passagem metodoDeBusca(ResultSet registro, Passagem entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Passagem> listarPassageirosDeUmaViagem(Integer idViagem) throws Exception {
		String query = "Select * from passagem where id_viagem = " + idViagem + " and cpf_cliente != ''";
		return Consulta(query);
	}
	
	public List<Passagem> listarPassagensCliente(String cpf) throws Exception {
		String query = "Select * from passagem where cpf_cliente = '" + cpf + "'";
		return Consulta(query);
	}
	
	public List<Passagem> listarPassagensDeUmaViagem(Integer idViagem) throws Exception{
		String query = "SELECT * FROM " + tabelaPassagem + " WHERE id_viagem=" + idViagem + " ORDER BY numero_poltrona";
		return Consulta(query);
	}
	
	public List<Passagem> PassagensMaisVendidadas(String consulta) throws Exception {
        conectar = conexao.abrirConexao();
        String query = consulta; 
        List<Passagem> listaDeEntidade = new ArrayList<>();
        try{
            PreparedStatement pst = conectar.prepareStatement(query);
            ResultSet registro = pst.executeQuery();
            while(registro.next()){
                Passagem passagem = new Passagem();
                viagemDAO = new ViagemDAO(conexao);
            	passagem.setViagem(viagemDAO.buscar(registro.getInt("id_viagem")));
            	passagem.setPreco(String.valueOf(registro.getDouble("preco")));
            	passagem.setQuantidade(registro.getInt("total_de_passagens"));
                listaDeEntidade.add(passagem);
            }
        }catch(SQLException e){
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally{
            conexao.fecharConexao();
        }
        return listaDeEntidade; 
    }
}
