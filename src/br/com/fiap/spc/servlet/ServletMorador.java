package br.com.fiap.spc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.spc.beans.Condominio;
import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.bo.MoradorBO;

public class ServletMorador implements Logica{
	
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		
		Morador m = new Morador();
		Condominio cond = new Condominio();
		
		m.setSenha(req.getParameter("senha")); 
		m.setNome(req.getParameter("nome"));
		m.setCpf(Long.parseLong(req.getParameter("cpf")));
		m.setEmail(req.getParameter("email"));
		m.setTelefone(Long.parseLong(req.getParameter("telefone")));
		cond.setCodigoCondominio(Integer.parseInt(req.getParameter("condominio")));
		m.setCondominio(cond);
		
		
		
		m.setRg(Integer.parseInt(req.getParameter("rg")));
		m.setRgDig(req.getParameter("rgDig")); 
		m.setDataNascimento(req.getParameter("dataNascimento"));
		m.setSexo(req.getParameter("sexo"));
		m.setNumeroApartamento(Integer.parseInt(req.getParameter("numero")));
		m.setTorre(req.getParameter("torre"));
				
		String resposta = MoradorBO.novoMorador(m);
		req.setAttribute("morador", resposta);

		
		return "/cadastro-morador.jsp";
	}

}
