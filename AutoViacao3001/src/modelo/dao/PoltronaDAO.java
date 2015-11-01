package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Poltrona;

public class PoltronaDAO extends BaseCrudDAO<Poltrona> {
	
	private final String tabelaPoltrona = "poltrona";
	private final String nomeDasColunasPoltrona = "id_onibus, numero";
	private final String tabelaOnibus = "onibus";
	private final String tabelaViagem = "viagem";
	
	public PoltronaDAO(ConexaoDAO conexao) {
		super(conexao);
	}

	@Override
	public String getQueryDeExiste(Poltrona poltrona) {
		return "SELECT DISTINCT * FROM " + tabelaPoltrona + " WHERE id_onibus = '" + poltrona.getIdOnibus() + "' AND numero = '" + poltrona.getNumero() + "'";
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
	public String getQueryDeRemocao(Poltrona poltrona) {
		return "DELETE FROM " + tabelaPoltrona + " WHERE id_onibus = " + poltrona.getIdOnibus() + " AND numero = " + poltrona.getNumero();
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
        	poltrona.setNumero(registro.getInt("numero"));
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
	
	public boolean removerPoltronasOnibus(Integer idOnibus) throws Exception{
		int exclusaoRealizada = 0;
		if (onibusPossuiViagem(idOnibus)){
			return false;
		} else{
			String query = "DELETE FROM " + tabelaPoltrona + " WHERE id_onibus = " + idOnibus;
			try {
				conectar = conexao.abrirConexao();
				PreparedStatement pst = conectar.prepareStatement(query);
				exclusaoRealizada = pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				conexao.fecharConexao();
			}
			return exclusaoRealizada != 0;
			
		}
		
	}
	
	public boolean onibusPossuiViagem(Integer idOnibus) throws Exception{
		String query = "SELECT * FROM " + tabelaOnibus + " o, " + tabelaViagem + " v WHERE v.id_onibus = o.id AND o.id = " + idOnibus;
		return existeAlgumResultado(query);
	}
	
	private boolean existeAlgumResultado(String query) throws Exception, SQLException {
		conectar = conexao.abrirConexao();

		PreparedStatement pst = conectar.prepareStatement(query);
		ResultSet registro = pst.executeQuery();
		
		conexao.fecharConexao();

		if (registro.next()) {
			return true;
		}

		return false;
	}

}
