package br.com.fiap.spc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.spc.beans.Condominio;
import br.com.fiap.spc.beans.Funcionario;
import br.com.fiap.spc.bo.FuncionarioBO;

public class CadastroFuncionario implements Logica {
	
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		Funcionario f = new Funcionario();
		
		f.setSenha(req.getParameter("senha"));
		f.setNome(req.getParameter("nome"));
		f.setCpf(Long.parseLong(req.getParameter("cpf")));
		f.setEmail(req.getParameter("email"));
		f.setTelefone(Long.parseLong(req.getParameter("telefone")));
		Condominio cond = new Condominio();
		cond.setCodigoCondominio(Integer.parseInt(req.getParameter("condominio")));
		f.setCondominio(cond);
		f.setRg(Integer.parseInt(req.getParameter("rg")));
		f.setRgDig(req.getParameter("rgDig"));
		f.setDataNascimento(req.getParameter("dataNascimento"));
		f.setSexo(req.getParameter("sexo"));
		f.setDataAdmissao(req.getParameter("dataAdmissao"));
		f.setFuncao(req.getParameter("funcao"));
		
		String resposta = FuncionarioBO.novoFuncionario(f);;
		req.setAttribute("funcionario", resposta);
		
		
		String passagem = "cadastro-funcionario.jsp";
		return passagem;
	}
	
}
