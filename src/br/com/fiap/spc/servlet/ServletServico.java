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

import br.com.fiap.spc.beans.Servico;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.bo.ServicoBO;
import br.com.fiap.spc.excecoes.Excecoes;


@WebServlet(urlPatterns="/servico")
public class ServletServico extends HttpServlet{

	private static final long serialVersionUID = -6343152267898050064L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String passagem;
			Usuario usuario = null;
			HttpSession session = req.getSession();	
		
			
			if(!(session.getAttribute("MORADOR") == null)) {
				usuario = (Usuario) session.getAttribute("MORADOR");
			}
			
			List<Servico> listServ = new ArrayList<Servico>();
			listServ = ServicoBO.buscarServico(usuario.getCodigoUsuario());
					
			session.setAttribute("listaServico", listServ);
			passagem = "WEB-INF/paginas/servico.jsp";		
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(passagem);
			dispatcher.forward(req, resp);
			
			
		}catch(Exception e) {
			System.out.println(Excecoes.tratarExcecao(e));			
		}
		
		
		
	}
	

}
