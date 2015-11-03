package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Passagem;

public class PassagemDAO extends BaseCrudDAO<Passagem>{
	
	private final String tabelaPassagem = "passagem";

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
        	passagem.setIdOnibus(registro.getInt("id_onibus"));
        	passagem.setNumeroPoltrona(registro.getInt("numero_poltrona"));
        	passagem.setPreco(registro.getDouble("preco"));
        	passagem.setIdViagem(registro.getInt("id_viagem"));
        	passagem.setCpfCliente(registro.getString("cpf_cliente"));
        	return passagem;
        } catch (SQLException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(PassagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Passagem passagem) {
		try {
			pst.setInt(1, passagem.getIdViagem());
			pst.setInt(2, passagem.getIdOnibus());
			pst.setInt(3, passagem.getNumeroPoltrona());
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

}
