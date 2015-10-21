package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Poltrona;

public class PoltronaDAO extends BaseCrudDAO<Poltrona> {
	
	private final String tabelaPoltrona = "poltrona";
	private final String nomeDasColunasPoltrona = "id_onibus, numero_poltrona";
	
	public PoltronaDAO(ConexaoDAO conexao) {
		super(conexao);
	}

	@Override
	public String getQueryDeExiste(Poltrona poltrona) {
		return "SELECT DISTINCT * FROM " + tabelaPoltrona + " WHERE id_onibus = '" + poltrona.getIdOnibus() + "' AND numero_poltrona = '" + poltrona.getNumero() + "'";
	}

	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaPoltrona + " (" + nomeDasColunasPoltrona + ") VALUES(?, ?)";
	}

	@Override
	public String getQueryDeAlteracao(Poltrona poltrona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeRemocao(Poltrona entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT * FROM " + tabelaPoltrona;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Poltrona getEntidade(ResultSet registro) {
		Poltrona poltrona;
        try {
        	poltrona = new Poltrona();
        	poltrona.setIdOnibus(registro.getInt("id_onibus"));
        	poltrona.setNumero(registro.getInt("numero_poltrona"));
        	return poltrona;
        } catch (SQLException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(PoltronaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Poltrona poltrona) {
		try {
			pst.setInt(1, poltrona.getIdOnibus());
		    pst.setInt(2, poltrona.getNumero());
		  
        } catch (SQLException ex) {
        	System.out.println("Erro ao incluir no banco - " + ex);
            Logger.getLogger(PoltronaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

	@Override
	public Poltrona metodoDeBusca(ResultSet registro, Poltrona entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
