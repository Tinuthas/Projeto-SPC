package br.com.fiap.spc.beans;

public class Encomenda implements Comparable<Encomenda>{
	
	private int codigoEncomenda;
	private Morador morador;
	private Funcionario funcionario;
	private int tipoEncomenda;
	private String tamanho;
	private String entrega;
	
	public int compareTo(Encomenda outro) {
		return -this.entrega.compareTo(outro.entrega);	
	}
	
	public Encomenda(int codigoEncomenda, Morador morador, Funcionario funcionario, int tipoEncomenda,
			String tamanho, String entrega) {
		super();
		this.codigoEncomenda = codigoEncomenda;
		this.morador = morador;
		this.funcionario = funcionario;
		this.tipoEncomenda = tipoEncomenda;
		this.tamanho = tamanho;
		this.entrega = entrega;
	}

	public Encomenda() {
		super();
	}

	public int getCodigoEncomenda() {
		return codigoEncomenda;
	}

	public void setCodigoEncomenda(int codigoEncomenda) {
		this.codigoEncomenda = codigoEncomenda;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public int getTipoEncomenda() {
		return tipoEncomenda;
	}

	public void setTipoEncomenda(int tipoEncomenda) {
		this.tipoEncomenda = tipoEncomenda;
	}
	

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getEntrega() {
		return entrega;
	}

	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}
	
	

}
