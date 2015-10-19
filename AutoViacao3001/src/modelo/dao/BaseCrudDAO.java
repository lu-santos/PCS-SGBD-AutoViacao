package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCrudDAO<T> {
	private String query;
    protected Connection conectar;
    protected ConexaoDAO conexao;
    
    public BaseCrudDAO() {}
    
    public BaseCrudDAO(ConexaoDAO conexao) {
        this.conexao = conexao;
    }
    
    public boolean incluir(T entidade) throws Exception {
    	int inclusaoRealizada = 0;
        conectar = conexao.abrirConexao();
        query = getQueryDeInclusao();
        PreparedStatement pst = conectar.prepareStatement(query);
        incluirDadosNoBanco(pst, entidade);
        inclusaoRealizada = pst.executeUpdate();
        conexao.fecharConexao();
        System.out.println("inclusão de moto: " + inclusaoRealizada);
        return inclusaoRealizada != 0;
    }
    
    public boolean alterar(T entidade) throws Exception {
    	int alteracaoRealizada = 0;
        conectar = conexao.abrirConexao();
        query = getQueryDeAlteracao(entidade);
        PreparedStatement pst = conectar.prepareStatement(query);
        incluirDadosNoBanco(pst, entidade);
        alteracaoRealizada = pst.executeUpdate();
        conexao.fecharConexao();
    	return alteracaoRealizada != 0;
    }
    
    public boolean remover(T entidade) throws Exception {
    	int exclusaoRealizada = 0;
    	query = getQueryDeRemocao(entidade);
        conectar = conexao.abrirConexao();
        try {
	        PreparedStatement pst = conectar.prepareStatement(query);
	        exclusaoRealizada = pst.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro ao excluir: " + e.getMessage());
        }finally{
            conexao.fecharConexao();
        }
        return exclusaoRealizada != 0;
    }
    
    public List<T> listar() throws Exception {
		conectar = conexao.abrirConexao();
        query = getQueryDeListar(); 
        List<T> listaDeEntidade = new ArrayList<>();
        try{
            PreparedStatement pst = conectar.prepareStatement(query);
            ResultSet registro = pst.executeQuery();
            while(registro.next()){
                T entidade;
                entidade = getEntidade(registro);
                listaDeEntidade.add(entidade);
            }
        }catch(SQLException e){
            System.out.println("Erro ao listar: " + e.getMessage());
        }finally{
            conexao.fecharConexao();
        }
        return listaDeEntidade; 
    }
    
    public T buscar(Object identificador) throws Exception {
        conectar = conexao.abrirConexao();
        query = getQueryDeBusca(identificador); 
        T entidade = null;
        try{
            PreparedStatement pst = conectar.prepareStatement(query);
            ResultSet registro = pst.executeQuery();
            entidade = metodoDeBusca(registro, entidade);
        }catch(SQLException e){
            System.out.println("Erro ao pesquisar registro: " + e.getMessage());
        }finally{
            conexao.fecharConexao();
        }
        return entidade; 
    }
    
    public List<T> Consulta(String consulta) throws Exception {
        conectar = conexao.abrirConexao();
        query = consulta; 
        List<T> listaDeEntidade = new ArrayList<>();
        try{
            PreparedStatement pst = conectar.prepareStatement(query);
            ResultSet registro = pst.executeQuery();
            while(registro.next()){
                T entidade = getEntidade(registro);
                listaDeEntidade.add(entidade);
            }
        }catch(SQLException e){
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally{
            conexao.fecharConexao();
        }
        return listaDeEntidade; 
    }
    
    public abstract String getQueryDeExiste(T entidade);
    public abstract String getQueryDeInclusao();
    public abstract String getQueryDeAlteracao(T entidade);
    public abstract String getQueryDeRemocao(T entidade);
    public abstract String getQueryDeListar();
    public abstract String getQueryDeBusca(Object identificador);
    public abstract T getEntidade(ResultSet registro);
    public abstract void incluirDadosNoBanco(PreparedStatement pst, T entidade);
    public abstract T metodoDeBusca(ResultSet registro, T entidade);
}
