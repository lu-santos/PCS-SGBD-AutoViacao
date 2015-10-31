package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Onibus;
import modelo.entidade.Viagem;

public class OnibusDAO extends BaseCrudDAO<Onibus> {

	private final String tabelaOnibus = "onibus";
	private final String nomeDasColunasOnibus = "placa, modelo, fabricante, ano, capacidade, tipo_leito, ar_condicionado, banheiro, frigobar, dvd";
	private final String tabelaViagem = "viagem";
	private final String tabelaPassagem = "passagem";
	
	public OnibusDAO(ConexaoDAO conexao) {
		super(conexao);
	}
	
	
	public Integer incluirComRetornoDeId(Onibus onibus) throws Exception {
		Integer idOnibus = null;
        conectar = conexao.abrirConexao();
        String query = getQueryDeInclusao();
        PreparedStatement pst = conectar.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        incluirDadosNoBanco(pst, onibus);
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        while (rs.next()){
        	idOnibus = rs.getInt("id");
        }        
        conexao.fecharConexao();
        return idOnibus;
    }

	@Override
	public String getQueryDeExiste(Onibus entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeInclusao() {
		return "INSERT INTO " + tabelaOnibus + " (" + nomeDasColunasOnibus + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	public String getQueryDeAlteracao(Onibus onibus) {
		return "UPDATE " + tabelaOnibus + " SET placa = ?, modelo = ?, fabricante = ?, ano = ?, capacidade = ?, tipo_leito = ?, ar_condicionado = ?, banheiro = ?, frigobar = ? WHERE id_onibus = " + onibus.getIdOnibus();
	}

	@Override
	public String getQueryDeRemocao(Onibus onibus) {
		return "DELETE FROM onibus WHERE id = " + onibus.getIdOnibus() + "; DELETE FROM " + tabelaOnibus + " WHERE id_onibus = " + onibus.getIdOnibus();
	}

	@Override
	public String getQueryDeListar() {
		return "SELECT * FROM " + tabelaOnibus;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		return "SELECT * FROM " + tabelaOnibus + " LEFT JOIN " + tabelaViagem + " USING (id) LEFT JOIN " + tabelaPassagem + " USING (id_viagem) WHERE id = " + identificador;
	}
	
	@Override
	public Onibus metodoDeBusca(ResultSet registro, Onibus entidade) {
		  try {
			  List<Viagem> viagens = new ArrayList<>();
			  Integer idOnibus = null;
			  while(registro.next()){
				  if(!idOnibus.equals(registro.getInt("id"))) {
					  entidade = getEntidade(registro);
				  }
				  ViagemDAO viagemDAO = new ViagemDAO();
				  viagens.add(viagemDAO.getEntidade(registro));
				  idOnibus = registro.getInt("id");
			  }
			  entidade.setViagens(viagens);
		} catch (SQLException e) {
			System.out.println("Erro no método de busca: " + e.getMessage());
			e.printStackTrace();
		}
		  return entidade;
	}

	@Override
	public Onibus getEntidade(ResultSet registro) {
		Onibus onibus;
        try {
        	onibus = new Onibus();
        	onibus.setIdOnibus(registro.getInt("id"));
        	onibus.setPlaca(registro.getString("placa"));
        	onibus.setModelo(registro.getString("modelo"));
        	onibus.setFabricante(registro.getString("fabricante"));
        	onibus.setAno(registro.getInt("ano"));
        	onibus.setCapacidade(registro.getInt("capacidade"));
        	onibus.setTipoLeito(registro.getString("tipo_leito"));
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
		    pst.setString(6, entidade.getTipoLeito());
		    pst.setBoolean(7, entidade.isArCondicionado());
		    pst.setBoolean(8, entidade.isBanheiro());
		    pst.setBoolean(9, entidade.isFrigobar());
		    pst.setBoolean(10, entidade.isDvd());
        } catch (SQLException ex) {
        	System.out.println("Erro ao incluir no banco - " + ex);
            Logger.getLogger(OnibusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

}
