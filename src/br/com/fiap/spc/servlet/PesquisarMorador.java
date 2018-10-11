package br.com.fiap.spc.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.bo.UsuarioBO;

@WebServlet(urlPatterns = "/pesquisarMorador")
public class PesquisarMorador implements Logica {

	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		HttpSession session = req.getSession();

		String passagem = null;

		listaUsuario = UsuarioBO.buscarUsuarioNome(req.getParameter("pesquisa"));

		if (!(listaUsuario == null)) {
			passagem = "WEB-INF/paginas/resultado-busca.jsp";
			session.setAttribute("listaUsuario", listaUsuario);
		} else {
			passagem = "pesquisar-morador.jsp";
		}

		session.setAttribute("listaUsuario", listaUsuario);
		return passagem;

	}
}
