package br.com.fiap.spc.excecoes;

public class Excecoes extends Exception {

	private static final long serialVersionUID = -6819500802503147202L;

	public Excecoes(String mensagem, Exception e) {
		super(mensagem, e);
		this.print();
		e.printStackTrace();
		// Envia e-mail para o responsável
	}
	
	public Excecoes(String mensagem) {
		super(mensagem);
		// Envia e-mail para o responsável
	}
	
	public static String tratarExcecao(Exception e) {
		if (e.getClass().getName().equals("java.lang.NumberFormatException")) {
			return "Número de formato inválido!";
		}else if (e.getClass().getName().equals("java.sql.SQLException")) {
			return "Não foi possível conectar no banco de dados! Algum problema de conexão";
		}else if(e.getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")) { 
			return "Violação de chave primaria";
		}else if(e.getClass().getName().equals("javax.servlet.ServletException")) { 
			return "A lógica de negócios causou uma exceção";
		}else if(e.getClass().getName().equals("ServletException")) { 
			return "Erro de Servlet";
		}else if(e.getClass().getName().equals("javax.el.MethodNotFoundException")) { 
			return "Erro de método não encontrado";
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
