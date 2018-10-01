package br.com.fiap.spc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.beans.Servico;
import br.com.fiap.spc.bo.ServicoBO;
import br.com.fiap.spc.excecoes.Excecoes;

@WebServlet(urlPatterns="/agendarServ")
public class ServletAgendarServ extends HttpServlet{

	private static final long serialVersionUID = 8369171065403999301L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			Servico serv = new Servico();
			HttpSession session = req.getSession();
			
			String data;
			String passagem;
			
			Morador m = new Morador();
			m = (Morador) session.getAttribute("MORADOR");
			
			serv.setMorador(m);
			serv.setTipoServico(Integer.parseInt(req.getParameter("tipo")));
			data = req.getParameter("dataInicio") +" "+ req.getParameter("horaInicio");
			System.out.println(req.getParameter("horaInicio"));
			serv.setInicio(data);
			data = req.getParameter("dataTermino") +" "+ req.getParameter("horaTermino");
			serv.setTermino(data);		
			
			String resposta = ServicoBO.novoServico(serv);
			if(resposta != null) {
				passagem = "WEB-INF/paginas/sucesso.jsp";
			}else {
				passagem = "WEB-INF/paginas/agendar-servico.jsp";
			}

			RequestDispatcher dispatcher = req.getRequestDispatcher(passagem);
			dispatcher.forward(req, resp);
			
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e);
				System.out.println(Excecoes.tratarExcecao(e));
			}
		
	}
}
