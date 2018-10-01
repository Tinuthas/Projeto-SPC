package br.com.fiap.spc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Encomenda;

import br.com.fiap.spc.dao.EncomendaDAO;

/**
 * Classe respons�vel por padronizar e controlar as regras de n�gocio do Encomenda
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.EncomendaDAO
 * @see	br.com.fiap.spc.beans.Encomenda
 *
 */
public class EncomendaBO {
	
	/**
	 * M�todo respons�vel pelo:
	 * 	Verificar se o funcion�rio � valido
	 * 	Verificar se o morador � v�lido
	 * 	Verificar se o tipo de encomenda � valido
	 * 	Verificar se o tamanho � valido
	 * 	Padronizar colocando o tamanho em alto
	 * 	Inserir c�digo de encomenda verificando o maior
	 *  Inserir padr�o de data
	 * 	Gravar encomenda
	 * 
	 * @param recebe um objeto enco da classe Encomenda
	 * @return retorna uma String da classe EncomendaDAO
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public static String novoEncomenda(Encomenda enco) throws Exception {
		
		EncomendaDAO dao = new EncomendaDAO();

		// VERIFICA��O
		if(enco.getFuncionario().getCodigoUsuario() != FuncionarioBO.buscarFuncionario(enco.getFuncionario().getCodigoUsuario()).getCodigoUsuario()) {
			return "Funcion�rio inv�lido!";
		}
		
		if(enco.getMorador().getCodigoUsuario() != MoradorBO.buscarMorador(enco.getFuncionario().getCodigoUsuario()).getCodigoUsuario()) {
			return "Morador inv�lido!";
		}
		
		if(enco.getTipoEncomenda() < 1 || enco.getTipoEncomenda() > 3) {
			return "Tipo inv�lido!";
		}
		
		if(enco.getTamanho() == null || enco.getTamanho().length() > 30) {
			return "Tamanho inv�lido!";
		}
		
		// PADRONIZA��O
		enco.setTamanho(enco.getTamanho().toUpperCase());
		
		
		// INSERINDO O MAIOR CODIGO DE ENCOMENDA DE ACRESCIMO 1 DO ANTERIOR
		int codigo = dao.maiorCodigo();
		if(codigo==0) {
			enco.setCodigoEncomenda(1);;
		}else {
			enco.setCodigoEncomenda(codigo + 1);
		}
		
		// DATA SYSDATE
		enco.setEntrega("SYSDATE");
		
		// GRAVANDO ENCOMENDA
		String resposta = dao.gravar(enco);
		dao.fechar();
		
		return resposta;
	}
	
	/**
	 * M�todo respons�vel pelo: 
	 * Consultar encomenda
	 * Verificar o valor da consulta
	 * 
	 * @param recebe codigo int do codigo da encomenda
	 * @return
	 * @throws Exception
	 * @author Marcus V. Galdino
	 */
	
	public static Encomenda buscar(int codigo) throws Exception {
		
		EncomendaDAO dao = new EncomendaDAO();
		Encomenda enco = new Encomenda();
		
		enco = dao.consultar(codigo);
		dao.fechar();
		if(enco.getCodigoEncomenda()==0) {
			return null;
		}else {
			return enco;
		}
		
		
	}
	
	/**
	 * M�todo respons�vel pelo:
	 * consultar a lista de encomendas do morador
	 * Verificar lista
	 * 
	 * @param recebe codigo int do morador
	 * @return retorna uma lista das encomendas do morador
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public static List<Encomenda> buscarEncomendaMorador(int codigo) throws Exception {
		List<Encomenda> listaEncomenda = new ArrayList<Encomenda>();
		EncomendaDAO dao = new EncomendaDAO();

		listaEncomenda = dao.consultaEncoMorador(codigo);
		
		if(listaEncomenda == null) {
			return null;
		}
		dao.fechar();
		return listaEncomenda;
		
	}
	
	/**
	 * M�todo respons�vel pelo:
	 * consultar a lista de encomendas do funcionario registrou
	 * Verificar lista
	 * 
	 * @param recebe codigo int do funcionario
	 * @return retorna uma lista das encomendas do funcionario
	 * @throws Exception valida a exce��o checked
	 */
	
	public static List<Encomenda> buscarEncomendaFuncionario(int codigo) throws Exception {
		List<Encomenda> listaEncomenda = new ArrayList<Encomenda>();
		EncomendaDAO dao = new EncomendaDAO();

		listaEncomenda = dao.consultaEncoFunc(codigo);
	
		dao.fechar();
		return listaEncomenda;
		
	}
}
