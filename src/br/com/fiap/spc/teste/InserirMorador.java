package br.com.fiap.spc.teste;

import javax.swing.JOptionPane;

import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.bo.CondominioBO;
import br.com.fiap.spc.bo.MoradorBO;
import br.com.fiap.spc.excecoes.Excecoes;


public class InserirMorador {

	
	
	public static void main (String[] args) {
		
		try {
			Morador m = new Morador();
			
				m.setSenha(JOptionPane.showInputDialog("senha")); 
				m.setNome(JOptionPane.showInputDialog("nome"));
				m.setCpf(Long.parseLong(JOptionPane.showInputDialog("cpf")));
				m.setEmail(JOptionPane.showInputDialog("email"));
				m.setTelefone(Long.parseLong(JOptionPane.showInputDialog("telefone")));
			
				m.setCondominio(CondominioBO.buscarCondominio(Integer.parseInt(JOptionPane.showInputDialog("Codigo Condominio"))));


				m.setRg(Integer.parseInt(JOptionPane.showInputDialog("rg")));
				m.setRgDig(JOptionPane.showInputDialog("rgDig")); 
				m.setDataNascimento(JOptionPane.showInputDialog("dataNascimento"));
				m.setSexo(JOptionPane.showInputDialog("sexo"));
				m.setNumeroApartamento(Integer.parseInt(JOptionPane.showInputDialog("numero")));
				m.setTorre(JOptionPane.showInputDialog("torre"));
				
				
				
			System.out.println(MoradorBO.novoMorador(m));
			
			
		} catch (Exception e) {
			System.out.println(Excecoes.tratarExcecao(e));
		}
		
		
		
	}

}
