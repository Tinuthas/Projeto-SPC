package br.com.fiap.spc.beans;

public class Condominio implements Comparable<Condominio>{
	
	private int codigoCondominio;
	private Endereco endereco;
	private String razaoSocial;
	private long cnpj;
	private int ds_tipo_condominio;
	private long telefone;
	private String email;
	
	public int compareTo(Condominio outro) {
		return -this.razaoSocial.compareTo(outro.razaoSocial);	
	}
	
	public Condominio(int codigoCondominio, Endereco endereco, String razaoSocial, long cnpj, int ds_tipo_condominio, 
			int numeroEndereco, String uf, String cidade, String bairro, long telefone, String email) {
		super();
		this.codigoCondominio = codigoCondominio;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.ds_tipo_condominio = ds_tipo_condominio;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}
	
	public Condominio(int codigoCondominio) {
		super();
	}
	
	public Condominio() {
		super();
	}

	public int getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public int getDs_tipo_condominio() {
		return ds_tipo_condominio;
	}

	public void setDs_tipo_condominio(int ds_tipo_condominio) {
		this.ds_tipo_condominio = ds_tipo_condominio;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}
