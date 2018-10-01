package br.com.fiap.spc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logica {
	
	String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	
}
