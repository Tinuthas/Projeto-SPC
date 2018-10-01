package br.com.fiap.spc.beans;


public class Comunicado implements Comparable<Comunicado>{
	
	private int numeroEnvio;
	private Pessoa pessoa;
	private String assunto;
	private String descricao;
	private String data;
	private String inicio;
	private String termino;
	private String local;
	
	public int compareTo(Comunicado outro) {
		return -this.data.compareTo(outro.data);	
	}
	
	public Comunicado(int numeroEnvio, Pessoa pessoa, String assunto, String descricao, String data, String inicio,
			String termino, String local) {
		super();
		this.numeroEnvio = numeroEnvio;
		this.pessoa = pessoa;
		this.assunto = assunto;
		this.descricao = descricao;
		this.data = data;
		this.inicio = inicio;
		this.termino = termino;
		this.local = local;
	}

	public Comunicado() {
		super();
	}

	public int getNumeroEnvio() {
		return numeroEnvio;
	}

	public void setNumeroEnvio(int numeroEnvio) {
		this.numeroEnvio = numeroEnvio;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getTermino() {
		return termino;
	}

	public void setTermino(String termino) {
		this.termino = termino;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	

	
	
	

	

}
	
	
	
