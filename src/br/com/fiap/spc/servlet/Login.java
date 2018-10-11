package br.com.fiap.spc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.spc.beans.Funcionario;
import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.bo.FuncionarioBO;
import br.com.fiap.spc.bo.MoradorBO;

public class Login implements Logica {

	@Override
	public String redireciona(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Morador m = new Morador();
		Funcionario f = new Funcionario();

		String email = req.getParameter("email");
		String senha = req.getParameter("senha");

		m.setEmail(email);
		m.setSenha(senha);
		f.setEmail(email);
		f.setSenha(senha);
		m = (Morador) MoradorBO.verificarMorador(m);

		f = (Funcionario) FuncionarioBO.verificarFuncionario(f);

		Usuario usuario = new Usuario();

		String passagem = null;
		String tipoUsuario = null;

		HttpSession session = req.getSession();
		
		if (m != null) {
			tipoUsuario = "MORADOR";
			usuario = m;
			passagem = "WEB-INF/paginas/chatbot.jsp";
		} else if (f != null) {
			tipoUsuario = "FUNCIONARIO";
			usuario = f;
			passagem = "WEB-INF/paginas/chatbot.jsp";
		} else {
			tipoUsuario = "INVALIDO";
			passagem = "login.jsp";
		}

		session.setAttribute(tipoUsuario, usuario);
		return passagem;

	}

}
