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

@WebServlet(urlPatterns = "/cadastroVinculo")
public class CadastroVinculo implements Logica {

	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		List<Vinculo> listaVinculo = new ArrayList<Vinculo>();
		Vinculo v = new Vinculo();
		HttpSession session = req.getSession();

		Morador m = new Morador();
		m = (Morador) session.getAttribute("MORADOR");

		String passagem = null;

		v.setNome(req.getParameter("nome"));
		v.setCpf(Long.parseLong(req.getParameter("cpf")));
		v.setEmail(req.getParameter("email"));
		v.setSenha(req.getParameter("senha"));
		v.setTelefone(Long.parseLong(req.getParameter("telefone")));
		v.setLogradouro(req.getParameter("logradouro"));

		String mensagem = VinculoBO.novoVinculo(v);

		listaVinculo = VinculoBO.buscarVinculo(m.getCodigoUsuario());

		if (mensagem != null) {
			passagem = "WEB-INF/paginas/vinculo.jsp";
			session.setAttribute("listaUsuario", listaVinculo);
			session.setAttribute("vinculoRegistrado", mensagem);
		} else {
			session.removeAttribute("vinculoRegistrado");
		}

		return passagem;

	}

}
