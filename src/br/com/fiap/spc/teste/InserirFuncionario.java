package br.com.fiap.spc.teste;

import javax.servlet.http.HttpServlet;
import javax.swing.JOptionPane;

import br.com.fiap.spc.beans.Funcionario;
import br.com.fiap.spc.bo.CondominioBO;
import br.com.fiap.spc.bo.FuncionarioBO;
import br.com.fiap.spc.excecoes.Excecoes;

public class InserirFuncionario extends HttpServlet{
	
	private static final long serialVersionUID = 1684752191787479804L;

	public static void main (String[] args) {
	
		try {
			Funcionario f = new Funcionario();
			
			f.setSenha(JOptionPane.showInputDialog("senha"));
			f.setNome(JOptionPane.showInputDialog("nome"));
			f.setCpf(Long.parseLong(JOptionPane.showInputDialog("cpf")));
			f.setEmail(JOptionPane.showInputDialog("email"));
			f.setTelefone(Long.parseLong(JOptionPane.showInputDialog("telefone")));
			f.setCondominio(CondominioBO.buscarCondominio((Integer.parseInt(JOptionPane.showInputDialog("condominio")))));
			f.setRg(Integer.parseInt(JOptionPane.showInputDialog("rg")));
			f.setRgDig(JOptionPane.showInputDialog("rgDig"));
			f.setDataNascimento(JOptionPane.showInputDialog("dataNascimento"));
			f.setSexo(JOptionPane.showInputDialog("sexo"));
			f.setDataAdmissao(JOptionPane.showInputDialog("dataAdmissao"));
			f.setFuncao(JOptionPane.showInputDialog("funcao"));
			
			System.out.println(FuncionarioBO.novoFuncionario(f));
		} catch (Exception e) {
			System.out.println(Excecoes.tratarExcecao(e));
		}
	
	
	}

}
