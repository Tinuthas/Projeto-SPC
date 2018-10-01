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

import br.com.fiap.spc.beans.Encomenda;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.bo.EncomendaBO;
import br.com.fiap.spc.excecoes.Excecoes;

@WebServlet(urlPatterns="/encomenda")
public class ServletEncomenda extends HttpServlet{

	private static final long serialVersionUID = -4839691860599544340L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String passagem = null;
			Usuario usuario = null;
			HttpSession session = req.getSession();
			List<Encomenda> listEnco = new ArrayList<Encomenda>();
			
			if(!(session.getAttribute("MORADOR") == null)) {
				usuario = (Usuario) session.getAttribute("MORADOR");		
				System.out.println(usuario.getCodigoUsuario());
				listEnco = EncomendaBO.buscarEncomendaMorador(usuario.getCodigoUsuario());
				
			}else if (!(session.getAttribute("FUNCIONARIO")==null)) {
				usuario = (Usuario) session.getAttribute("FUNCIONARIO");
				System.out.println(usuario.getCodigoUsuario());
				listEnco = EncomendaBO.buscarEncomendaFuncionario(usuario.getCodigoUsuario());	
				
			}

			
			
			session.setAttribute("listaEncomenda", listEnco);
			passagem = "WEB-INF/paginas/encomenda.jsp";
	
			
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(passagem);
			dispatcher.forward(req, resp);
		
		}catch(Exception e) {
			System.out.println(Excecoes.tratarExcecao(e));
		}
		
	}

}
