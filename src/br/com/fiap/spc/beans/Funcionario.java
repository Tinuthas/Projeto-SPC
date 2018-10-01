package br.com.fiap.spc.beans;

public class Funcionario extends Pessoa {
	
	private String dataAdmissao;
	private String funcao;
	

	public Funcionario(int codigoUsuario, String senha, String nome, long cpf, String email, long telefone,
			Condominio condominio, int rg, String rgDig, String dataNascimento, String sexo, String dataAdmissao,
			String funcao) {
		super(codigoUsuario, senha, nome, cpf, email, telefone, condominio, rg, rgDig, dataNascimento, sexo);
		this.dataAdmissao = dataAdmissao;
		this.funcao = funcao;
	}
	public Funcionario() {
		super();
	}
	public String getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(String dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	
	
	
	
	
	
}
