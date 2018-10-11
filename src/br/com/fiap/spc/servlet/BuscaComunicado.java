package br.com.fiap.spc.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Comunicado;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.bo.ComunicadoBO;

@WebServlet(urlPatterns = "/comunicado")
public class BuscaComunicado implements Logica {

	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String passagem;
		Usuario usuario = null;
		HttpSession session = req.getSession();

		if (!(session.getAttribute("MORADOR") == null)) {
			usuario = (Usuario) session.getAttribute("MORADOR");
		} else if (!(session.getAttribute("FUNCIONARIO") == null)) {
			usuario = (Usuario) session.getAttribute("FUNCIONARIO");
		}

		List<Comunicado> listComu = new ArrayList<Comunicado>();
		listComu = ComunicadoBO.buscarComunicado(usuario.getCodigoUsuario());

		session.setAttribute("listaComunicado", listComu);
		passagem = "WEB-INF/paginas/comunicado.jsp";

		return passagem;

	}

}
