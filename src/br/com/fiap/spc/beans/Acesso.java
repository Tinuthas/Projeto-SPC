package br.com.fiap.spc.beans;

public class Acesso implements Comparable<Acesso>{
	
	private int codigoAcesso;
	private Usuario usuario;
	private String dataAcesso;
	
	public int compareTo(Acesso outro) {
		if(this.codigoAcesso < outro.codigoAcesso) {
			return - 1;
		}else if (this.codigoAcesso > outro.codigoAcesso) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public Acesso(int codigoAcesso, Usuario usuario, String dataAcesso ) {
		super();
		this.codigoAcesso = codigoAcesso;
		this.usuario = usuario;
		this.dataAcesso = dataAcesso;
	}

	public Acesso() {
		super();
	}

	public int getCodigoAcesso() {
		return codigoAcesso;
	}

	public void setCodigoAcesso(int codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(String dataAcesso) {
		this.dataAcesso = dataAcesso;
	}
	
	
	
}
