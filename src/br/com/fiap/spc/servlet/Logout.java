package br.com.fiap.spc.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/logout")
public class Logout implements Logica {

	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		session.removeAttribute("MORADOR");
		session.removeAttribute("FUNCIONARIO");

		String passagem = "login.jsp";
		return passagem;

	}

}
