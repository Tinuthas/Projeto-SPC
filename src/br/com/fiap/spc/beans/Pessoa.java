package br.com.fiap.spc.beans;


public class Pessoa extends Usuario{
	
	private Condominio condominio;
	private int rg;
	private String rgDig;
	private String dataNascimento;
	private String sexo;
	
	public Pessoa(int codigoUsuario, String senha, String nome, long cpf, String email, long telefone,
			Condominio condominio, int rg, String rgDig, String dataNascimento, String sexo) {
		super(codigoUsuario, senha, nome, cpf, email, telefone);
		this.condominio = condominio;
		this.rg = rg;
		this.rgDig = rgDig;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}
	public Pessoa() {
		super();
	}
	public Condominio getCondominio() {
		return condominio;
	}
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	public int getRg() {
		return rg;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public String getRgDig() {
		return rgDig;
	}
	public void setRgDig(String rgDig) {
		this.rgDig = rgDig;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	

}
