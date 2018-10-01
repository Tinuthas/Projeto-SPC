package br.com.fiap.spc.bo;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import br.com.fiap.spc.beans.Acesso;
import br.com.fiap.spc.dao.AcessoDAO;

/**
 * Classe responsável por padronizar e controlar as regras de négocio do Acesso
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.AcessoDAO
 * @see	br.com.fiap.spc.beans.Acesso
 *
 */

public class AcessoBO {
	
	/**
	 * Método resposnsável pelo:
	 * 	Inserir chave primária e verificar
	 * 	verificar Usuario
	 * 	Gravar acesso 
	 * 
	 * 
	 * @param recebe a um objeto Acesso
	 * @return retorna uma String enviada pela classe AcessoDAO
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static String acessoNovo(Acesso a) throws Exception{
		
		AcessoDAO dao = new AcessoDAO();
		
		//INSERIR CHAVE PRIMÁRIA E VERIFICAR 
		int codigo = dao.maiorCodigo();
		if(codigo == 0 ) {
			a.setCodigoAcesso(1);
		}else {
			a.setCodigoAcesso(codigo + 1);
		}
		
		// VERIFICAR USUARIO
		if(a.getUsuario().getCodigoUsuario() != UsuarioBO.buscarUsuario(a.getUsuario().getCodigoUsuario()).getCodigoUsuario()) {
			return "Usuario Inválido";
		}
		
		/* VERIFICAÇÃO PARA DATA 
		
		// VALIDAÇÃO
		if(a.getDataAcesso() == null || a.getDataAcesso().length() != 10) {
			return "Data de Acesso inválido";
		}
		
		// PADRONIZAÇÃO
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date data = df.parse(a.getDataAcesso());
		df.applyPattern("dd/MM/yyyy");
		a.setDataAcesso(df.format(data));
		
		*/
		
		// GRAVANDO O ACESSO
		String mensagem = dao.gravar(a);
		dao.fechar();
		
		return mensagem;
	}
	
	
	
}
