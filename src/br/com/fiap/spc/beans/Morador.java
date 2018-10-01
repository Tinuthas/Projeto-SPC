package br.com.fiap.spc.beans;


public class Morador extends Pessoa{
	
	private int numeroApartamento;
	private String torre;
	
	public int compareTo(Morador m) {
		return 0;
	}
	
	public Morador(int codigoUsuario, String senha, String nome, long cpf, String email, long telefone,
			Condominio condominio, int rg, String rgDig, String dataNascimento, String sexo, int numeroApartamento,
			String torre) {
		super(codigoUsuario, senha, nome, cpf, email, telefone, condominio, rg, rgDig, dataNascimento, sexo);
		this.numeroApartamento = numeroApartamento;
		this.torre = torre;
	}
	public Morador(int codigoUsuario) {
		super();
	}
	public Morador() {
		super();
	}
	
	public int getNumeroApartamento() {
		return numeroApartamento;
	}
	public void setNumeroApartamento(int numeroApartamento) {
		this.numeroApartamento = numeroApartamento;
	}
	public String getTorre() {
		return torre;
	}
	public void setTorre(String torre) {
		this.torre = torre;
	}
	
	
	
	
	
	
}
