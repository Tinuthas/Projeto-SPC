package br.com.fiap.spc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.beans.Vinculo;
import br.com.fiap.spc.bo.VinculoBO;
import br.com.fiap.spc.excecoes.Excecoes;

@WebServlet(urlPatterns="/cadastroVinculo")
public class ServletCadastrarVinculo extends HttpServlet {
	

	private static final long serialVersionUID = -2807831620463963517L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try  {
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
		
		if(mensagem != null ) {
			passagem = "WEB-INF/paginas/vinculo.jsp";
			session.setAttribute("listaUsuario", listaVinculo);
			session.setAttribute("vinculoRegistrado", mensagem);
		}else {
			session.removeAttribute("vinculoRegistrado");
		}
		

		RequestDispatcher dispatcher = req.getRequestDispatcher(passagem);
		dispatcher.forward(req, resp);
		
		
		}catch (Exception e) {
			System.out.println(Excecoes.tratarExcecao(e));
		}
	}
	
}
