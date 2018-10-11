package br.com.fiap.spc.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Servico;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.bo.ServicoBO;

@WebServlet(urlPatterns = "/servico")
public class BuscaServico implements Logica {

	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String passagem;
		Usuario usuario = null;
		HttpSession session = req.getSession();

		if (!(session.getAttribute("MORADOR") == null)) {
			usuario = (Usuario) session.getAttribute("MORADOR");
		}

		List<Servico> listServ = new ArrayList<Servico>();
		listServ = ServicoBO.buscarServico(usuario.getCodigoUsuario());

		session.setAttribute("listaServico", listServ);
		passagem = "WEB-INF/paginas/servico.jsp";

		return passagem;

	}

}
