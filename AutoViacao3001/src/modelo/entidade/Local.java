package modelo.entidade;

public class Local {
	private Integer id;
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	


	@Override
	public boolean equals(Object local) 
	{
	    if (local instanceof Local) 
	    {
	      Local localComparado = (Local) local;
	      if (this.id.equals(localComparado.getId()))
	         return true;
	    }
	    return false;
	}
	


}
