package br.com.fiap.spc.teste;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.dao.UsuarioDAO;
import br.com.fiap.spc.excecoes.Excecoes;

public class TestePequisa {

	public static void main(String[] args) {
		
		try {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		String nome = JOptionPane.showInputDialog("Digite o nome");
		
		UsuarioDAO dao = new UsuarioDAO();
		
		System.out.println(nome);
		
		listaUsuario = dao.consultarUsuarioNome(nome);
		
		for (Usuario usuario : listaUsuario) {

			System.out.println(usuario.getNome());

		}
		
		
		}catch (Exception e ) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Excecoes.tratarExcecao(e));
		}

	}

}
