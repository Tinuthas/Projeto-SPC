package br.com.fiap.spc.excecoes;

public class Excecoes extends Exception {

	private static final long serialVersionUID = -6819500802503147202L;

	public Excecoes(String mensagem, Exception e) {
		super(mensagem, e);
		this.print();
		e.printStackTrace();
		// Envia e-mail para o respons�vel
	}
	
	public Excecoes(String mensagem) {
		super(mensagem);
		// Envia e-mail para o respons�vel
	}
	
	public static String tratarExcecao(Exception e) {
		if (e.getClass().getName().equals("java.lang.NumberFormatException")) {
			return "N�mero de formato inv�lido!";
		}else if (e.getClass().getName().equals("java.sql.SQLException")) {
			return "N�o foi poss�vel conectar no banco de dados! Algum problema de conex�o";
		}else if(e.getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")) { 
			return "Viola��o de chave primaria";
		}else if(e.getClass().getName().equals("javax.servlet.ServletException")) { 
			return "A l�gica de neg�cios causou uma exce��o";
		}else if(e.getClass().getName().equals("ServletException")) { 
			return "Erro de Servlet";
		}else if(e.getClass().getName().equals("javax.el.MethodNotFoundException")) { 
			return "Erro de m�todo n�o encontrado";
		}else if(e.getClass().getName().equals("org.apache.jasper.JasperException")) { 
			return "Erro de Servlet";
		}else {
			return "Mensagem do arquiteto";
		}
	}
	
	public void print() {
		System.out.println("-------- TRANTANDO ERRO ---------");
		System.out.println(getMessage());
		System.out.println("PrintStackTrace: ");
	}

}
