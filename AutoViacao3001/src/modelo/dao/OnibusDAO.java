package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Admin;
import modelo.entidade.Onibus;
import modelo.entidade.Passagem;
import modelo.entidade.Viagem;

public class OnibusDAO extends BaseCrudDAO<Onibus> {

	private final String tabelaOnibus = "onibus";
	private final String nomeDasColunasOnibus = "placa, modelo, fabricante, ano, capacidade, ar_condicionado, banheiro, frigobar, dvd";
	private final String tabelaViagem = "viagem";
	private final String tabelaPassagem = "passagem";
	
	public OnibusDAO(ConexaoDAO conexao) {
		super(conexao);
	}

	@Override
	public String getQueryDeExiste(Onibus entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaOnibus + " (" + nomeDasColunasOnibus + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	public String getQueryDeAlteracao(Onibus onibus) {
		return "UPDATE " + tabelaOnibus + " SET placa = ?, modelo = ?, fabricante = ?, ano = ?, capacidade = ?, ar_condicionado = ?, banheiro = ?, frigobar = ? WHERE id_onibus = " + onibus.getIdOnibus();
	}

	@Override
	public String getQueryDeRemocao(Onibus onibus) {
		return "DELETE FROM poltrona WHERE id_onibus = " + onibus.getIdOnibus() + "; DELETE FROM " + tabelaOnibus + " WHERE id_onibus = " + onibus.getIdOnibus();
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT * FROM " + tabelaOnibus;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		return "SELECT * FROM " + tabelaOnibus + " LEFT JOIN " + tabelaViagem + " USING (id_onibus) LEFT JOIN " + tabelaPassagem + " USING (id_viagem) WHERE id_onibus = " + identificador;
	}
	
	@Override
	public Onibus metodoDeBusca(ResultSet registro, Onibus entidade) {
		  try {
			  List<Viagem> viagens = new ArrayList<>();
			  Integer idOnibus = null;
			  while(registro.next()){
				  if(!idOnibus.equals(registro.getInt("id_onibus"))) {
					  entidade = getEntidade(registro);
				  }
				  ViagemDAO viagemDAO = new ViagemDAO();
				  viagens.add(viagemDAO.getEntidade(registro));
				  idOnibus = registro.getInt("id_onibus");
			  }
			  entidade.setViagens(viagens);
		} catch (SQLException e) {
			System.out.println("Erro no m�todo de busca: " + e.getMessage());
			e.printStackTrace();
		}
		  return entidade;
	}

	@Override
	public Onibus getEntidade(ResultSet registro) {
		Onibus onibus;
        try {
        	onibus = new Onibus();
        	onibus.setIdOnibus(registro.getInt("id_onibus"));
        	onibus.setPlaca(registro.getString("placa"));
        	onibus.setModelo(registro.getString("modelo"));
        	onibus.setFabricante(registro.getString("fabricante"));
        	onibus.setAno(registro.getInt("ano"));
        	onibus.setCapacidade(registro.getInt("capacidade"));
        	onibus.setArCondicionado(registro.getBoolean("ar_condicionado"));
        	onibus.setBanheiro(registro.getBoolean("banheiro"));
        	onibus.setFrigobar(registro.getBoolean("frigobar"));
        	onibus.setDvd(registro.getBoolean("dvd"));	
        	return onibus;
        } catch (SQLException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(OnibusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}


	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Onibus entidade) {
		try {
			pst.setString(1, entidade.getPlaca());
		    pst.setString(2, entidade.getModelo());
		    pst.setString(3, entidade.getFabricante());
		    pst.setInt(4, entidade.getAno());
		    pst.setInt(5, entidade.getCapacidade());
		    pst.setBoolean(6, entidade.isArCondicionado());
		    pst.setBoolean(7, entidade.isBanheiro());
		    pst.setBoolean(8, entidade.isFrigobar());
		    pst.setBoolean(9, entidade.isDvd());
        } catch (SQLException ex) {
        	System.out.println("Erro ao incluir no banco - " + ex);
            Logger.getLogger(OnibusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

}