package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.DataUtil;
import modelo.entidade.Locais;
import modelo.entidade.Local;
import modelo.entidade.Viagem;

public class LocaisDAO extends BaseCrudDAO<Locais> {

	private final String tabelaLocais = "locais";

	public LocaisDAO(ConexaoDAO conexao) {
		super(conexao);
	}

	@Override
	public String getQueryDeExiste(Locais entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeInclusao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeAlteracao(Locais entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeRemocao(Locais entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeListar() {
		return "select * from " + tabelaLocais;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locais getEntidade(ResultSet registro) {
		Locais locais;
        try {
        	locais = new Locais();
        	locais.setIdLocais(registro.getInt("id"));
        	locais.setIdLocalOrigem(registro.getInt("id_local_origem"));
        	locais.setIdLocalDestino(registro.getInt("id_local_destino"));
        	locais.setDistancia(registro.getDouble("distancia"));
        	
        	return locais;
        } catch (SQLException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Locais entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Locais metodoDeBusca(ResultSet registro, Locais entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
