package br.com.fiap.spc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Comunicado;
import br.com.fiap.spc.beans.Pessoa;
import br.com.fiap.spc.bo.ComunicadoBO;
import br.com.fiap.spc.bo.PessoaBO;
import br.com.fiap.spc.excecoes.Excecoes;
@WebServlet(urlPatterns="/escreverComu")
public class ServletEscreverComu extends HttpServlet{
	
	private static final long serialVersionUID = -774670840250012883L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			Comunicado comu = new Comunicado();
			HttpSession session = req.getSession();
			Pessoa p = new Pessoa();
			String passagem = null;
			
			if(!(session.getAttribute("MORADOR") == null)) {
				p = (Pessoa) session.getAttribute("MORADOR");
				
			}else if(!(session.getAttribute("FUNCIONARIO") == null)) {
				p = (Pessoa) session.getAttribute("FUNCIONARIO");
			}
			p = PessoaBO.buscarPessoa(p.getCodigoUsuario());
			
			comu.setPessoa(PessoaBO.buscarPessoa(p.getCodigoUsuario()));
			comu.setDescricao(req.getParameter("descricao"));
			comu.setAssunto(req.getParameter("assunto"));
			comu.setLocal(req.getParameter("local"));
			
			
			
			
			String resposta = ComunicadoBO.comunicadoNovo(comu);
			if(resposta != null) {
				passagem = "WEB-INF/paginas/sucesso.jsp";
			}else {
				passagem = "WEB-INF/paginas/escrever-comunicado.jsp";
			}

			RequestDispatcher dispatcher = req.getRequestDispatcher(passagem);
			dispatcher.forward(req, resp);
			
			}catch(Exception e) {

				System.out.println(Excecoes.tratarExcecao(e));
			}
		
	}
}
