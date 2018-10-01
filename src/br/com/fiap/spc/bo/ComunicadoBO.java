package br.com.fiap.spc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Comunicado;
import br.com.fiap.spc.dao.ComunicadoDAO;
/**
 * Classe respons�vel por padronizar e controlar as regras de n�gocio do Comunicado
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.ComunicadoDAO
 * @see	br.com.fiap.spc.beans.Comunicado
 *
 */
public class ComunicadoBO {
	
	/**
	 * M�todo resposns�vel pelo:
	 * 	Validar tamanho da descri��o e verificar se n�o � null
	 *  Validar se c�digo da pessoa existe
	 *  Validar tamanho do assunto
	 *  Validar local e verificar se n�o � null
	 *  Validar se tem inicio e t�rmino e verificar se inicio e menor que t�rmino
	 * 	Converte local em caixa alta
	 * 	Converte assunto em caixa alta
	 *  Inserir padr�o na data de envio
	 *  Insere e verifica a chave prim�ria
	 *  Grava o comunicado
	 * 
	 * @param recebe comu um objeto Comunicado
	 * @return retorna uma String enviada pela classe ComunicadoDAO
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	public static String comunicadoNovo(Comunicado comu) throws Exception {
		
		ComunicadoDAO dao = new ComunicadoDAO();
				
		//VALIDA��O
		if(comu.getDescricao().length() > 1000 || comu.getDescricao().equals(null)) {
			return "Descri��o inv�lida!";
		}
		
		if(comu.getPessoa().getCodigoUsuario() != PessoaBO.buscarPessoa(comu.getPessoa().getCodigoUsuario()).getCodigoUsuario()) {
			return "Pessoa inv�lida!";
		}
		
		if(comu.getAssunto().length() > 30) {
			return "Assunto inv�lido!";
		}
		
		if(comu.getLocal() == null || comu.getAssunto().length() > 30) {
			return "Local inv�lido!";
		}
		
		if(comu.getInicio() != null && comu.getTermino() != null) {
			if(dao.stringToDate(comu.getInicio()).getTime() > dao.stringToDate(comu.getTermino()).getTime()) {
				return "Data de incio � maior que a de t�rmino!";
			}
		}
		//PADRONIZANDO 
		comu.setLocal(comu.getLocal().toUpperCase());
		comu.setAssunto(comu.getAssunto().toUpperCase());
		
		//COLOCANDO DATA DE ENVIO COMO PADRO�O SYSDATE
		comu.setData("SYSDATE");
		
		//INSERIR CHAVE PRIM�RIA E VERIFICAR 
		int codigo = dao.maiorCodigo();
		if(codigo == 0) {
			comu.setNumeroEnvio(1);
		}else {
			comu.setNumeroEnvio(codigo + 1);
		}
		
		// GRAVANDO COMUNICADO
		
		String mensagem = dao.gravar(comu);
		dao.fechar();
		
		return mensagem;
		
	}
	
	/**
	 * M�todo resposns�vel pelo:
	 * 	verificar se a lista � vazia
	 * 	returna a lista de comunicados
	 * 
	 * @param recebe codigo do condominio
	 * @return retorna uma lista de comunicados do condomini
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public static List<Comunicado> buscarComunicado(int codigo) throws Exception {
		List<Comunicado> listaComunicado = new ArrayList<Comunicado>();
		ComunicadoDAO dao = new ComunicadoDAO();
		
		listaComunicado = dao.consultarComunicado(codigo);
		dao.fechar();
		
		// VERIFICANDO CODIGO
		if(listaComunicado.equals(null)) {
			return null;
		}
		
		return listaComunicado;
	}
	
}
