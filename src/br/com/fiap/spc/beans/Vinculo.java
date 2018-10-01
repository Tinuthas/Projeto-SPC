package br.com.fiap.spc.beans;

public class Vinculo extends Usuario {
	
	private Morador morador;
	private String logradouro;
	
	public Vinculo(int codigoUsuario, String senha, String nome, long cpf, String email, long telefone, Morador morador, String logradouro) {
		super(codigoUsuario, senha, nome, cpf, email, telefone);
		this.morador = morador;
		this.logradouro = logradouro;
	}
	public Vinculo() {
		super();
	}
	public Morador getMorador() {
		return morador;
	}
	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	
	
	
	
	
	
	
	
	
}
	