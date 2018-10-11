package br.com.fiap.spc.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.beans.Vinculo;
import br.com.fiap.spc.bo.VinculoBO;

@WebServlet(urlPatterns = "/vinculos")
public class BuscaVinculo implements Logica {

	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		List<Vinculo> listaVinculo = new ArrayList<Vinculo>();
		HttpSession session = req.getSession();

		Morador m = new Morador();
		m = (Morador) session.getAttribute("MORADOR");

		String passagem = null;

		listaVinculo = VinculoBO.buscarVinculo(m.getCodigoUsuario());

		if (listaVinculo != null) {
			passagem = "WEB-INF/paginas/vinculo.jsp";
			session.setAttribute("listaUsuario", listaVinculo);
		} else {
			passagem = "WEB-INF/paginas/vinculo.jsp";
		}

		return passagem;

	}
}
