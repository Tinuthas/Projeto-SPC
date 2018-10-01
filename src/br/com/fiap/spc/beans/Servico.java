package br.com.fiap.spc.beans;


public class Servico implements Comparable<Servico>{
	
	private int codigoServico;
	private Morador morador;
	private int tipoServico;
	private String inicio;
	private String termino;
	
	public int compareTo(Servico outro) {
		return -this.inicio.compareTo(outro.inicio);	
	}
	
	public Servico(int codigoServico, Morador morador, int tipoServico, String inicio,
			String termino) {
		super();
		this.codigoServico = codigoServico;
		this.morador = morador;
		this.tipoServico = tipoServico;
		this.inicio = inicio;
		this.termino = termino;
	}

	public Servico() {
		super();
	}

	public int getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(int codigoServico) {
		this.codigoServico = codigoServico;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public int getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(int tipoServico) {
		this.tipoServico = tipoServico;
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
	
	
	

}
