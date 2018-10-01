package br.com.fiap.spc.teste;

import javax.swing.JOptionPane;

import br.com.fiap.spc.beans.Acesso;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.bo.AcessoBO;
import br.com.fiap.spc.excecoes.Excecoes;

public class TesteGravarAcesso {

	public static void main(String[] args) {
		
		try {
			Acesso a = new Acesso();
			a.setUsuario(new Usuario(Integer.parseInt(JOptionPane.showInputDialog("Digite seu número de usuário"))));
			
			System.out.println(AcessoBO.acessoNovo(a)+" de número "+a.getCodigoAcesso() );
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Excecoes.tratarExcecao(e));
		}
		
	}

}
