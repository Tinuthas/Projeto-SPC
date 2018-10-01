package br.com.fiap.spc.teste;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.spc.beans.Condominio;
import br.com.fiap.spc.beans.Endereco;
import br.com.fiap.spc.bo.CondominioBO;
import br.com.fiap.spc.excecoes.Excecoes;

@WebServlet(urlPatterns="/servlet-condominio")
public class InserirCondominio extends HttpServlet{

	private static final long serialVersionUID = -6886789527600566263L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			Condominio cond = new Condominio();
			cond.setCodigoCondominio(Integer.parseInt(req.getParameter("")));
			cond.setRazaoSocial(req.getParameter("razaoSocial"));
			cond.setCnpj(Long.parseLong(req.getParameter("cnpj")));
			cond.setDs_tipo_condominio(Integer.parseInt(req.getParameter("tipo")));
			cond.setTelefone(Long.parseLong(req.getParameter("telefone")));
			cond.setEmail(req.getParameter("email"));
			cond.setEndereco(
					new Endereco(
						Integer.parseInt(req.getParameter("")),
						req.getParameter("logradouro"),
						Integer.parseInt(req.getParameter("numero")),
						Integer.parseInt(req.getParameter("cep")),
						req.getParameter("estado"),
						req.getParameter("cidade"),
						req.getParameter("bairro")
					));
			

			//System.out.println(bo.novoCondominio(cond));
			CondominioBO.novoCondominio(cond);
			
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/paginas/condominio.jsp");
			dispatcher.forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Excecoes.tratarExcecao(e));
		}

	}

}
