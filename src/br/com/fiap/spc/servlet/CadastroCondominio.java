package br.com.fiap.spc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.spc.beans.Condominio;
import br.com.fiap.spc.beans.Endereco;
import br.com.fiap.spc.bo.CondominioBO;

public class CadastroCondominio implements Logica {

	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Condominio cond = new Condominio();
		Endereco ende = new Endereco();
		cond.setRazaoSocial(req.getParameter("razaoSocial"));
		cond.setCnpj(Long.parseLong(req.getParameter("cnpj")));
		cond.setDs_tipo_condominio(Integer.parseInt(req.getParameter("tipo")));
		cond.setTelefone(Long.parseLong(req.getParameter("telefone")));
		cond.setEmail(req.getParameter("email"));
		ende.setLogradouro(req.getParameter("logradouro"));
		ende.setNumeroEndereco(Integer.parseInt(req.getParameter("numero")));
		ende.setCidade(req.getParameter("cidade"));
		ende.setUf(req.getParameter("estado"));
		ende.setCep(Integer.parseInt(req.getParameter("cep")));
		ende.setBairro(req.getParameter("bairro"));
		cond.setEndereco(ende);

		String mensagem = CondominioBO.novoCondominio(cond);
		String passagem = null;
		if (mensagem.equals("OK")) {
			passagem = "WEB-INF/paginas/condominio.jsp";
		} else {
			passagem = "cadastro-condominio.html";
		}

		return passagem;
	}

}
