package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidade.Local;

public class LocalDAO extends BaseCrudDAO<Local> {

	private final String tabelaLocal = "local";

	public LocalDAO(ConexaoDAO conexao) {
		super(conexao);
	}

	@Override
	public String getQueryDeExiste(Local entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeInclusao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeAlteracao(Local entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeRemocao(Local entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT * FROM " + tabelaLocal;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Local getEntidade(ResultSet registro) {
		Local local = null;
		try {
			local = new Local();
			local.setId(registro.getInt("id_local"));
			local.setNome(registro.getString("nome"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return local;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Local entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public Local metodoDeBusca(ResultSet registro, Local entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
