package br.com.fiap.spc.teste;

import javax.swing.JOptionPane;

import br.com.fiap.spc.beans.Servico;
import br.com.fiap.spc.bo.MoradorBO;
import br.com.fiap.spc.bo.ServicoBO;
import br.com.fiap.spc.excecoes.Excecoes;

public class InserirServico {
	

	public static void main(String[] args) {


		try {
			
			Servico serv = new Servico();
			
			serv.setMorador(MoradorBO.buscarMorador(Integer.parseInt(JOptionPane.showInputDialog("codigoMorador"))));
			serv.setTipoServico(Integer.parseInt(JOptionPane.showInputDialog("tipo")));
			serv.setInicio(JOptionPane.showInputDialog("inicio"));
			serv.setTermino(JOptionPane.showInputDialog("termino"));
			
			////21/07/2006 9:51:00
			
			System.out.println(ServicoBO.novoServico(serv));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Excecoes.tratarExcecao(e));
		}

	}

}
