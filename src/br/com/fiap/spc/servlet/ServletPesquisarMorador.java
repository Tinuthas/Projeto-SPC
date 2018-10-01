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

import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.bo.UsuarioBO;
import br.com.fiap.spc.excecoes.Excecoes;

@WebServlet(urlPatterns="/pesquisarMorador")
public class ServletPesquisarMorador extends HttpServlet {

	private static final long serialVersionUID = -7334159094065565078L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			HttpSession session = req.getSession();
			
			String passagem = null;
			
			listaUsuario = UsuarioBO.buscarUsuarioNome(req.getParameter("pesquisa"));
			
			
			if(!(listaUsuario == null)) {
				passagem = "WEB-INF/paginas/resultado-busca.jsp";
				session.setAttribute("listaUsuario", listaUsuario);
			}else {
				passagem = "pesquisar-morador.jsp";
			}
			
			session.setAttribute("listaUsuario", listaUsuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher(passagem);
			dispatcher.forward(req, resp);
			
			}catch(Exception e) {
				System.out.println(Excecoes.tratarExcecao(e));
			}
		
	}
}
