package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.entidade.Onibus;
import modelo.entidade.Passagem;
import modelo.entidade.Viagem;

public class ViagemDAO extends BaseCrudDAO<Viagem>{
	
	public ViagemDAO() {}
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
	
	public ViagemDAO(ConexaoDAO conexao) {
		super(conexao);
	}

	@Override
	public String getQueryDeExiste(Viagem entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeInclusao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeAlteracao(Viagem entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeRemocao(Viagem entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeListar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryDeBusca(Object identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Viagem getEntidade(ResultSet registro) {
		Viagem viagem;
        try {
        	viagem = new Viagem();
        	viagem.setIdViagem(registro.getInt("id_viagem"));
        	viagem.setDataPartida(DATE_FORMAT.format(registro.getDate(("data_partida"))));
        	viagem.setHoraPartida(TIME_FORMAT.format(registro.getTime("hora_partida")));
        	viagem.setDataChegada(DATE_FORMAT.format(registro.getString("data_chegada")));
        	viagem.setHoraChegada(TIME_FORMAT.format(registro.getString("hora_chegada")));
        	viagem.setDistancia(registro.getDouble("distancia"));
        	viagem.setIdLocalPartida(registro.getInt("id_local_partida"));
        	viagem.setIdLocalDestino(registro.getInt("id_local_destino"));
        	viagem.setIdOnibus(registro.getInt("onibus"));
        	viagem.setCpfMotorista(registro.getString("cpf_motorista"));
        	return viagem;
        } catch (SQLException | ParseException ex) {
        	System.out.println("Erro ao pegar entidade no banco - " + ex);
            Logger.getLogger(ViagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	@Override
	public void incluirDadosNoBanco(PreparedStatement pst, Viagem entidade) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Viagem metodoDeBusca(ResultSet registro, Viagem entidade) {
		  try {
			  List<Passagem> passagens = new ArrayList<>();  
			  Integer idViagem = null;
			  while(registro.next()){
				  if(!idViagem.equals(registro.getInt("id_viagem")))
					  entidade = getEntidade(registro);
				  PassagemDAO passagemDAO = new PassagemDAO();
				  passagens.add(passagemDAO.getEntidade(registro));
				  idViagem = registro.getInt("id_viagem");
			  }
				  entidade.setPassagens(passagens);
		} catch (SQLException e) {
			System.out.println("Erro no método de busca: " + e.getMessage());
			e.printStackTrace();
		}
		  return entidade;
	}
}
