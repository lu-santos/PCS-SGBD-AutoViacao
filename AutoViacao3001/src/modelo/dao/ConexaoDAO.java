package modelo.dao;

import java.sql.Connection;

public interface ConexaoDAO {
	 public Connection abrirConexao() throws Exception;
	 public void fecharConexao() throws Exception; 
}
