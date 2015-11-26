package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgres implements ConexaoDAO {
	private static final String bdURL = "jdbc:postgresql://localhost:5432/AutoViacao3001";
    private final String usuario = "postgres";
    private final String senha = "1234";
    private final String bdSqlDriver = "org.postgresql.Driver";
    private Connection conexao;
    
    @Override
    public Connection abrirConexao() throws Exception{
        try{
           Class.forName(bdSqlDriver);
        }catch(ClassNotFoundException e){
            System.out.println("Classe nao encontrada");
            throw e;
        }
        try{
        	if (conexao == null || conexao.isClosed()){
        		conexao = DriverManager.getConnection(bdURL, usuario, senha);
        	}
        }catch(SQLException e){
            System.out.println("Erro na conexao");
            throw e;
        }
        return conexao;
    }
    
    @Override
    public void fecharConexao() throws SQLException{
        try{
            conexao.close();
        }catch(SQLException e){
            throw e;
        }    
    }
}
