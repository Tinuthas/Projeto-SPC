package br.com.fiap.spc.beans;

public class Usuario implements Comparable<Usuario> {
	
	protected int codigoUsuario;
	private String senha;
	private String nome;
	private long cpf;
	private String email;
	private long telefone;
	
	public int compareTo(Usuario outro) {
		return -this.nome.compareTo(outro.nome);	
	}
	
	public Usuario(int codigoUsuario, String senha, String nome, long cpf, String email, long telefone) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.senha = senha;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}
	public Usuario(	int codigoUsuario) {
		super();
		this.codigoUsuario = codigoUsuario;
	}
	public Usuario() {
		super();
	}
	public int getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	
}	