package br.com.fiap.spc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRetorno implements Logica {
	
	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		
		return "WEB-INF/paginas/chatbot.jsp";
	}
	
}
