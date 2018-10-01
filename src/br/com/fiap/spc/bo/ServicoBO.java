package br.com.fiap.spc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Servico;
import br.com.fiap.spc.dao.ServicoDAO;
/**
 * Classe respons�vel por padronizar e controlar as regras de n�gocio do Servico
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.ServicoDAO
 * @see	br.com.fiap.spc.beans.Servico
 *
 */
public class ServicoBO {
	
	/**
	 * M�todo respons�vel pelo:
	 * 	Verificar o inicio do servi�o se existe
	 *  Verificar o tipo de servi�o se encaixa
	 *  Verificar se o morador existe
	 *  Insere o maior codigo consultando o maior codigo
	 *  Gravar Servico
	 * 
	 * @param recebe objeto serv do Servico
	 * @return retorna uma String da classe ServicoDAO
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public static String novoServico(Servico serv) throws Exception {
		
		ServicoDAO dao = new ServicoDAO();
		
		// VALIDA��O
//		if(serv.getTermino() != null && serv.getInicio() != null) {
//			if(dao.stringToDate(serv.getInicio()).getTime() > dao.stringToDate(serv.getTermino()).getTime()) {
//				return "Data de incio � maior que a de t�rmino";
//			}
//		}
		
		if(serv.getInicio() == null) {
			return "Inicio inv�lido";
		}
		
		if(serv.getTipoServico() < 0 || serv.getTipoServico() > 6 ) {
			return "Tipo de servi�o inv�lido";
		}
		
		if(serv.getMorador().getCodigoUsuario() != MoradorBO.buscarMorador(serv.getMorador().getCodigoUsuario()).getCodigoUsuario()) {
			return "Morador inv�lido";
		}
		
		
		// PADRONIZANDO 
		
		
		// INSERIR CHAVE PRIM�RIA E VERIFICAR 
		int codigo = dao.maiorCodigo();
		if(codigo == 0) {
			serv.setCodigoServico(1);
		}else {
			serv.setCodigoServico(codigo + 1);
		}
		
		String mensagem = dao.gravar(serv);
		dao.fechar();
		
		return mensagem;
		
	}
	
	/**
	 * M�todo respons�vel pelo:
	 *  Consultar a lista de servi�os 
	 *  Verificar se a lista de servi�o n�o � vazia
	 * 
	 * @param recebe codigo int do servico
	 * @return retorna um lista de objetos servicos
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public static List<Servico> buscarServico(int codigo) throws Exception {
		List<Servico> listaServico = new ArrayList<Servico>();
		ServicoDAO dao = new ServicoDAO();
		
		listaServico = dao.consultaServico(codigo);
		dao.fechar();
		
		if(listaServico != null) {
			return listaServico;
		}else {
			return null;	
		}
		
	}
	

}
