package br.com.fiap.spc.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Encomenda;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.bo.EncomendaBO;

@WebServlet(urlPatterns = "/encomenda")
public class BuscaEncomenda implements Logica {

	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String passagem = null;
		Usuario usuario = null;
		HttpSession session = req.getSession();
		List<Encomenda> listEnco = new ArrayList<Encomenda>();

		if (!(session.getAttribute("MORADOR") == null)) {
			usuario = (Usuario) session.getAttribute("MORADOR");
			System.out.println(usuario.getCodigoUsuario());
			listEnco = EncomendaBO.buscarEncomendaMorador(usuario.getCodigoUsuario());

		} else if (!(session.getAttribute("FUNCIONARIO") == null)) {
			usuario = (Usuario) session.getAttribute("FUNCIONARIO");
			System.out.println(usuario.getCodigoUsuario());
			listEnco = EncomendaBO.buscarEncomendaFuncionario(usuario.getCodigoUsuario());

		}

		session.setAttribute("listaEncomenda", listEnco);
		passagem = "WEB-INF/paginas/encomenda.jsp";

		return passagem;

	}

}
