package br.com.fiap.spc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Encomenda;

import br.com.fiap.spc.dao.EncomendaDAO;

/**
 * Classe responsável por padronizar e controlar as regras de négocio do Encomenda
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.EncomendaDAO
 * @see	br.com.fiap.spc.beans.Encomenda
 *
 */
public class EncomendaBO {
	
	/**
	 * Método responsável pelo:
	 * 	Verificar se o funcionário é valido
	 * 	Verificar se o morador é válido
	 * 	Verificar se o tipo de encomenda é valido
	 * 	Verificar se o tamanho é valido
	 * 	Padronizar colocando o tamanho em alto
	 * 	Inserir código de encomenda verificando o maior
	 *  Inserir padrão de data
	 * 	Gravar encomenda
	 * 
	 * @param recebe um objeto enco da classe Encomenda
	 * @return retorna uma String da classe EncomendaDAO
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static String novoEncomenda(Encomenda enco) throws Exception {
		
		EncomendaDAO dao = new EncomendaDAO();

		// VERIFICAÇÃO
		if(enco.getFuncionario().getCodigoUsuario() != FuncionarioBO.buscarFuncionario(enco.getFuncionario().getCodigoUsuario()).getCodigoUsuario()) {
			return "Funcionário inválido!";
		}
		
		if(enco.getMorador().getCodigoUsuario() != MoradorBO.buscarMorador(enco.getFuncionario().getCodigoUsuario()).getCodigoUsuario()) {
			return "Morador inválido!";
		}
		
		if(enco.getTipoEncomenda() < 1 || enco.getTipoEncomenda() > 3) {
			return "Tipo inválido!";
		}
		
		if(enco.getTamanho() == null || enco.getTamanho().length() > 30) {
			return "Tamanho inválido!";
		}
		
		// PADRONIZAÇÃO
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
	 * Método responsável pelo: 
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
	 * Método responsável pelo:
	 * consultar a lista de encomendas do morador
	 * Verificar lista
	 * 
	 * @param recebe codigo int do morador
	 * @return retorna uma lista das encomendas do morador
	 * @throws Exception valida a exceção checked
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
	 * Método responsável pelo:
	 * consultar a lista de encomendas do funcionario registrou
	 * Verificar lista
	 * 
	 * @param recebe codigo int do funcionario
	 * @return retorna uma lista das encomendas do funcionario
	 * @throws Exception valida a exceção checked
	 */
	
	public static List<Encomenda> buscarEncomendaFuncionario(int codigo) throws Exception {
		List<Encomenda> listaEncomenda = new ArrayList<Encomenda>();
		EncomendaDAO dao = new EncomendaDAO();

		listaEncomenda = dao.consultaEncoFunc(codigo);
	
		dao.fechar();
		return listaEncomenda;
		
	}
}
