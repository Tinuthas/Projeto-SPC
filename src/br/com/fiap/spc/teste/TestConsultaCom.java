package br.com.fiap.spc.teste;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.spc.beans.Comunicado;
import br.com.fiap.spc.bo.ComunicadoBO;
import br.com.fiap.spc.excecoes.Excecoes;


public class TestConsultaCom {
	
	public static void main(String[] args) {
		try {
			List<Comunicado> listComu = new ArrayList<Comunicado>();
			listComu = ComunicadoBO.buscarComunicado(Integer.parseInt(JOptionPane.showInputDialog("Digite codigo")));
			for(Comunicado comu : listComu) {
				System.out.println(comu.getAssunto());

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Excecoes.tratarExcecao(e));
		}

	}
}
