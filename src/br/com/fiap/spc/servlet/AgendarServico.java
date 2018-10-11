package br.com.fiap.spc.servlet;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.beans.Servico;
import br.com.fiap.spc.bo.ServicoBO;


@WebServlet(urlPatterns = "/agendarServ")
public class AgendarServico implements Logica {

	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Servico serv = new Servico();
		HttpSession session = req.getSession();

		String data;
		String passagem;

		Morador m = new Morador();
		m = (Morador) session.getAttribute("MORADOR");

		serv.setMorador(m);
		serv.setTipoServico(Integer.parseInt(req.getParameter("tipo")));
		data = req.getParameter("dataInicio") + " " + req.getParameter("horaInicio");
		System.out.println(req.getParameter("horaInicio"));
		serv.setInicio(data);
		data = req.getParameter("dataTermino") + " " + req.getParameter("horaTermino");
		serv.setTermino(data);

		String resposta = ServicoBO.novoServico(serv);
		if (resposta != null) {
			passagem = "WEB-INF/paginas/sucesso.jsp";
		} else {
			passagem = "WEB-INF/paginas/agendar-servico.jsp";
		}

		return passagem;

	}
}
