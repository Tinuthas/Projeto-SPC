package br.com.fiap.spc.teste;

import javax.servlet.http.HttpServlet;
import javax.swing.JOptionPane;

import br.com.fiap.spc.beans.Comunicado;
import br.com.fiap.spc.bo.ComunicadoBO;
import br.com.fiap.spc.bo.PessoaBO;
import br.com.fiap.spc.excecoes.Excecoes;

public class InserirComunicado extends HttpServlet{


	private static final long serialVersionUID = 6971837069785294764L;

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	public static void main (String[] args) {	
		try {
			Comunicado comu = new Comunicado();
			
			comu.setPessoa(PessoaBO.buscarPessoa(Integer.parseInt(JOptionPane.showInputDialog("codigo"))));
			comu.setDescricao(JOptionPane.showInputDialog("descricao"));
			comu.setInicio(JOptionPane.showInputDialog("dataInicio"));
			comu.setTermino(JOptionPane.showInputDialog("dataTermino"));
			comu.setLocal(JOptionPane.showInputDialog("local"));
			
			//21/07/2006 9:51:00
			
//			comu.setDescricao(req.getParameter("descricao"));
//			comu.setInicio(req.getParameter("dataInicio"));
//			comu.setTermino(req.getParameter("dataTermino"));
//			comu.setLocal(req.getParameter("local"));
			
			System.out.println(ComunicadoBO.comunicadoNovo(comu));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Excecoes.tratarExcecao(e));
		}

	}

}
