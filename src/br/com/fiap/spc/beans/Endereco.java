package br.com.fiap.spc.beans;

public class Endereco implements Comparable<Endereco>{
	
	private int codigoEndereco;
	private String logradouro; //logradouro
	private int numeroEndereco;
	private int cep;
	private String uf;
	private String cidade;
	private String bairro;
	
	public int compareTo(Endereco outro) {
		if(this.cep < outro.cep) {
			return - 1;
		}else if (this.cep > outro.cep) {
			return 1;
		}else {
			return 0;
		}
	}
	

	public Endereco(int codigoEndereco, String logradouro, int numeroEndereco, int cep, String uf, String cidade,
			String bairro) {
		super();
		this.codigoEndereco = codigoEndereco;
		this.logradouro = logradouro;
		this.numeroEndereco = numeroEndereco;
		this.cep = cep;
		this.uf = uf;
		this.cidade = cidade;
		this.bairro = bairro;
	}
	
	public Endereco() {
		super();
	}
	
	public Endereco(int i) {
		super();
	}

	public int getCodigoEndereco() {
		return codigoEndereco;
	}

	public void setCodigoEndereco(int codigoEndereco) {
		this.codigoEndereco = codigoEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(int numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	
	

}
