package modelo.entidade;

import modelo.dao.ConexaoPostgres;
import modelo.dao.LocalDAO;

public class Locais {
	private Integer idLocais;
	private Integer idLocalDestino;
	private Integer idLocalOrigem;
	private double distancia;
	private String label;
	private ConexaoPostgres conexao = new ConexaoPostgres();
	private LocalDAO lDAO = new LocalDAO(conexao);

	public Integer getIdLocais() {
		return idLocais;
	}

	public void setIdLocais(Integer idLocais) {
		this.idLocais = idLocais;
	}

	public Integer getIdLocalDestino() {
		return idLocalDestino;
	}

	public void setIdLocalDestino(Integer idLocalDestino) {
		this.idLocalDestino = idLocalDestino;
	}

	public Integer getIdLocalOrigem() {
		return idLocalOrigem;
	}

	public void setIdLocalOrigem(Integer idLocalOrigem) {
		this.idLocalOrigem = idLocalOrigem;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public String getLabel() {
		this.label = lDAO.getNomeLocal(idLocalOrigem) + " / " + lDAO.getNomeLocal(idLocalDestino) + " / " + distancia + " KM";
		return label;
	}
	
	

}
