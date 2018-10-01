package br.com.fiap.spc.teste;

import javax.swing.JOptionPane;

import br.com.fiap.spc.beans.Encomenda;
import br.com.fiap.spc.bo.EncomendaBO;
import br.com.fiap.spc.bo.FuncionarioBO;
import br.com.fiap.spc.bo.MoradorBO;
import br.com.fiap.spc.excecoes.Excecoes;

public class InserirEncomenda {

	public static void main(String[] args) {
		
		try {
		
			Encomenda enco = new Encomenda();
			
			enco.setMorador(MoradorBO.buscarMorador(Integer.parseInt(JOptionPane.showInputDialog("codigoMorador"))));
			enco.setFuncionario(FuncionarioBO.buscarFuncionario(Integer.parseInt(JOptionPane.showInputDialog("codigoFuncionario"))));
			enco.setTamanho(JOptionPane.showInputDialog("tamanho"));
			enco.setTipoEncomenda(Integer.parseInt(JOptionPane.showInputDialog("tipo")));
			
			System.out.println(EncomendaBO.novoEncomenda(enco));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Excecoes.tratarExcecao(e));
		}
		
		

	}

}
