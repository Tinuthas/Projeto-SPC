package br.com.fiap.spc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Retorno implements Logica {
	
	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String passagem = "WEB-INF/paginas/chatbot.jsp";
		return passagem;
	}
	
}
