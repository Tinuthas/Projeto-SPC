package br.com.fiap.spc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Comunicado;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.bo.ComunicadoBO;
import br.com.fiap.spc.excecoes.Excecoes;


@WebServlet(urlPatterns="/comunicado")
public class ServletComunicado extends HttpServlet{
	
	private static final long serialVersionUID = 6784398311206510761L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
		String passagem;
		Usuario usuario = null;
		HttpSession session = req.getSession();
		
		if(!(session.getAttribute("MORADOR") == null)) {
			usuario = (Usuario) session.getAttribute("MORADOR");
		}else if (!(session.getAttribute("FUNCIONARIO")==null)) {
			usuario = (Usuario) session.getAttribute("FUNCIONARIO");
		}
		
		List<Comunicado> listComu = new ArrayList<Comunicado>();
		listComu = ComunicadoBO.buscarComunicado(usuario.getCodigoUsuario());
		
		
		session.setAttribute("listaComunicado", listComu);
		passagem = "WEB-INF/paginas/comunicado.jsp";
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(passagem);
		dispatcher.forward(req, resp);
		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Excecoes.tratarExcecao(e));
		}
		
	}

}
