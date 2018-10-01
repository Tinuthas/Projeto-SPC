package br.com.fiap.spc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.dao.UsuarioDAO;
/**
 * Classe responsável por padronizar e controlar as regras de négocio do Usuario
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.UsuarioDAO
 * @see	br.com.fiap.spc.beans.Usuario
 *
 */
public class UsuarioBO {
	
	/**
	 * Método responsável pelo: 
	 *  Verifica senha o tamanho e se é vazia
	 *  Verifica email o tamanho e se é vazia
	 *  Verifica nome o tamanho e se é vazia
	 *  Verifica cpf se encaixa no tamanho
	 *  Verifica telefone se encaixa no tamanho
	 *  Padroniza email para caixa baixa
	 *  Padroniza o nome para caixa alto
	 *  Verificar se cpf já existe
	 *  Gravar Usuario
	 *  
	 * @param recebe um objeto u do Usuario
	 * @return retorna uma String da classe UsuarioDAO
	 * @throws Exception valida a exceção checked
	 */
	public static String novoUsuario(Usuario u) throws Exception {	
		UsuarioDAO dao = new UsuarioDAO();
		
		// SEM CÓDIGO DE USUÁRIO POIS PREFERIR COLOCAR NA CLASSE FILHA
		
		// VALIDAÇÃO
		if(u.getSenha() == null || u.getSenha().length() > 30) {
			return "Senha inválida";
		}
		if(u.getEmail() == null || u.getEmail().length() > 80) {
			return "E-mail inválido";
		}
		if(u.getNome() == null || u.getNome().length() > 60) {
			return "Nome inválido";
		}
		if(u.getCpf() < 10000000000L || u.getCpf() > 99999999999L) {
			return "Cpf Inválido";
		}
		if((u.getTelefone() < 10000000L || u.getTelefone() > 9999999999999L) && u.getTelefone() != 0 ){
			return "Telefone inválido";
		}
		
		// PADRONIZAÇÃO
		u.setEmail(u.getEmail().toLowerCase());
		u.setNome(u.getNome().toUpperCase());
		
		// REGRA DE NEGÓCIO
		if(u.getCpf() == dao.consultarUsuarioCpf(u.getCpf()).getCpf()) {
			return "Cpf já existe";
		}
		
		String mensagem = dao.gravarUsuario(u);
		dao.fechar();
		
		return mensagem;
	}
	
	/**
	 * Método responsável pelo:
	 *  Consultar o usuario se existe 
	 *  verificar se o usuario não é vazio
	 * 
	 * @param recebe o codigo int do usuario
	 * @return returna um objeto usuario
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static Usuario buscarUsuario(int codigo) throws Exception {
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario u = new Usuario();
		
		u = dao.consultarUsuario(codigo);
		dao.fechar();
		
		if(u.getCodigoUsuario() == 0) {
			return null;
		}else  {
			return u;
		}
		
		
	}
	
	/**
	 * Método responsável pelo:
	 *  Consultar email e senha do usuario
	 *  Verificar se o usuario é vazio
	 * @param receber o objeto u do usuário
	 * @return retornar um objeto Usuario
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static List<Usuario> buscarUsuarioNome(String nome) throws Exception {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		UsuarioDAO dao = new UsuarioDAO();
		
		// CONSULTAR USUÁRIO PELO NOME
		listaUsuario = dao.consultarUsuarioNome(nome);
		dao.fechar();
		return listaUsuario;
	}

}
