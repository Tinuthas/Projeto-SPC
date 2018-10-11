package br.com.fiap.spc.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Encomenda;
import br.com.fiap.spc.beans.Funcionario;
import br.com.fiap.spc.bo.EncomendaBO;
import br.com.fiap.spc.bo.FuncionarioBO;
import br.com.fiap.spc.bo.MoradorBO;

@WebServlet(urlPatterns = "/registrarEnco")
public class RegistroEncomenda implements Logica {

	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Encomenda enco = new Encomenda();
		Funcionario f = new Funcionario();
		String passagem = null;

		enco.setMorador(MoradorBO.buscarMorador(Integer.parseInt(req.getParameter("codigo"))));
		enco.setTamanho(req.getParameter("tamanho"));
		enco.setTipoEncomenda(Integer.parseInt(req.getParameter("tipo")));

		HttpSession session = req.getSession();
		f = (Funcionario) session.getAttribute("FUNCIONARIO");

		enco.setFuncionario(FuncionarioBO.buscarFuncionario(f.getCodigoUsuario()));

		String resposta = EncomendaBO.novoEncomenda(enco);
		if (resposta != null) {
			passagem = "WEB-INF/paginas/sucesso.jsp";
		} else {
			passagem = "WEB-INF/paginas/registrar-encomenda.jsp";
		}

		return passagem;

	}

}
