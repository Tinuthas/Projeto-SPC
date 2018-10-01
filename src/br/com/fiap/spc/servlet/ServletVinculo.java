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

import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.beans.Vinculo;
import br.com.fiap.spc.bo.VinculoBO;
import br.com.fiap.spc.excecoes.Excecoes;

@WebServlet(urlPatterns="/vinculos")
public class ServletVinculo extends HttpServlet {

	private static final long serialVersionUID = 2534047603496898679L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			List<Vinculo> listaVinculo = new ArrayList<Vinculo>();
			HttpSession session = req.getSession();
			
			Morador m = new Morador();
			m = (Morador) session.getAttribute("MORADOR");
			
			String passagem = null;
	
			
			
			listaVinculo = VinculoBO.buscarVinculo(m.getCodigoUsuario());
			
			if(listaVinculo != null) {
				passagem = "WEB-INF/paginas/vinculo.jsp";
				session.setAttribute("listaUsuario", listaVinculo);
			}else {
				passagem = "WEB-INF/paginas/vinculo.jsp";
			}
			

			RequestDispatcher dispatcher = req.getRequestDispatcher(passagem);
			dispatcher.forward(req, resp);
			
			}catch(Exception e) {
				System.out.println(Excecoes.tratarExcecao(e));
			}
		
	}
}
