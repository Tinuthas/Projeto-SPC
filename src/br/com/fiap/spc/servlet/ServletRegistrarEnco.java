package br.com.fiap.spc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import br.com.fiap.spc.beans.Encomenda;
import br.com.fiap.spc.beans.Funcionario;
import br.com.fiap.spc.bo.EncomendaBO;
import br.com.fiap.spc.bo.FuncionarioBO;
import br.com.fiap.spc.bo.MoradorBO;
import br.com.fiap.spc.excecoes.Excecoes;

@WebServlet(urlPatterns="/registrarEnco")
public class ServletRegistrarEnco extends HttpServlet{
	
	private static final long serialVersionUID = 3024187376175394022L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
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
			if(resposta != null) {
				passagem = "WEB-INF/paginas/sucesso.jsp";
			}else {
				passagem = "WEB-INF/paginas/registrar-encomenda.jsp";
			}

			RequestDispatcher dispatcher = req.getRequestDispatcher(passagem);
			dispatcher.forward(req, resp);
			
			}catch(Exception e) {
				System.out.println(Excecoes.tratarExcecao(e));
			}
		
	}
	
}
