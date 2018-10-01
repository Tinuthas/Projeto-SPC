package br.com.fiap.spc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Comunicado;
import br.com.fiap.spc.dao.ComunicadoDAO;
/**
 * Classe responsável por padronizar e controlar as regras de négocio do Comunicado
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.ComunicadoDAO
 * @see	br.com.fiap.spc.beans.Comunicado
 *
 */
public class ComunicadoBO {
	
	/**
	 * Método resposnsável pelo:
	 * 	Validar tamanho da descrição e verificar se não é null
	 *  Validar se código da pessoa existe
	 *  Validar tamanho do assunto
	 *  Validar local e verificar se não é null
	 *  Validar se tem inicio e término e verificar se inicio e menor que término
	 * 	Converte local em caixa alta
	 * 	Converte assunto em caixa alta
	 *  Inserir padrão na data de envio
	 *  Insere e verifica a chave primária
	 *  Grava o comunicado
	 * 
	 * @param recebe comu um objeto Comunicado
	 * @return retorna uma String enviada pela classe ComunicadoDAO
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	public static String comunicadoNovo(Comunicado comu) throws Exception {
		
		ComunicadoDAO dao = new ComunicadoDAO();
				
		//VALIDAÇÃO
		if(comu.getDescricao().length() > 1000 || comu.getDescricao().equals(null)) {
			return "Descrição inválida!";
		}
		
		if(comu.getPessoa().getCodigoUsuario() != PessoaBO.buscarPessoa(comu.getPessoa().getCodigoUsuario()).getCodigoUsuario()) {
			return "Pessoa inválida!";
		}
		
		if(comu.getAssunto().length() > 30) {
			return "Assunto inválido!";
		}
		
		if(comu.getLocal() == null || comu.getAssunto().length() > 30) {
			return "Local inválido!";
		}
		
		if(comu.getInicio() != null && comu.getTermino() != null) {
			if(dao.stringToDate(comu.getInicio()).getTime() > dao.stringToDate(comu.getTermino()).getTime()) {
				return "Data de incio é maior que a de término!";
			}
		}
		//PADRONIZANDO 
		comu.setLocal(comu.getLocal().toUpperCase());
		comu.setAssunto(comu.getAssunto().toUpperCase());
		
		//COLOCANDO DATA DE ENVIO COMO PADROÃO SYSDATE
		comu.setData("SYSDATE");
		
		//INSERIR CHAVE PRIMÁRIA E VERIFICAR 
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
	 * Método resposnsável pelo:
	 * 	verificar se a lista é vazia
	 * 	returna a lista de comunicados
	 * 
	 * @param recebe codigo do condominio
	 * @return retorna uma lista de comunicados do condomini
	 * @throws Exception valida a exceção checked
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
