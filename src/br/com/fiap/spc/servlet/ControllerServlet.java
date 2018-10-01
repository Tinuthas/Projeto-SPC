package br.com.fiap.spc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.spc.excecoes.Excecoes;

@WebServlet(urlPatterns="/controller")
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 6019275294370515412L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parametro = req.getParameter("enviar");
		String nomeDaClasse = "br.com.fiap.spc.servlet."+parametro;
		
		try {
			
			Class<?> classe = Class.forName(nomeDaClasse);
			
			Logica logica = (Logica) classe.newInstance();
			String pagina = logica.redireciona(req,resp);
			
			req.getRequestDispatcher(pagina).forward(req, resp);			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Excecoes.tratarExcecao(e));
		}
		
	}
	
}
