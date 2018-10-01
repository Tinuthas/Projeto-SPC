package br.com.fiap.spc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.dao.UsuarioDAO;
/**
 * Classe respons�vel por padronizar e controlar as regras de n�gocio do Usuario
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.UsuarioDAO
 * @see	br.com.fiap.spc.beans.Usuario
 *
 */
public class UsuarioBO {
	
	/**
	 * M�todo respons�vel pelo: 
	 *  Verifica senha o tamanho e se � vazia
	 *  Verifica email o tamanho e se � vazia
	 *  Verifica nome o tamanho e se � vazia
	 *  Verifica cpf se encaixa no tamanho
	 *  Verifica telefone se encaixa no tamanho
	 *  Padroniza email para caixa baixa
	 *  Padroniza o nome para caixa alto
	 *  Verificar se cpf j� existe
	 *  Gravar Usuario
	 *  
	 * @param recebe um objeto u do Usuario
	 * @return retorna uma String da classe UsuarioDAO
	 * @throws Exception valida a exce��o checked
	 */
	public static String novoUsuario(Usuario u) throws Exception {	
		UsuarioDAO dao = new UsuarioDAO();
		
		// SEM C�DIGO DE USU�RIO POIS PREFERIR COLOCAR NA CLASSE FILHA
		
		// VALIDA��O
		if(u.getSenha() == null || u.getSenha().length() > 30) {
			return "Senha inv�lida";
		}
		if(u.getEmail() == null || u.getEmail().length() > 80) {
			return "E-mail inv�lido";
		}
		if(u.getNome() == null || u.getNome().length() > 60) {
			return "Nome inv�lido";
		}
		if(u.getCpf() < 10000000000L || u.getCpf() > 99999999999L) {
			return "Cpf Inv�lido";
		}
		if((u.getTelefone() < 10000000L || u.getTelefone() > 9999999999999L) && u.getTelefone() != 0 ){
			return "Telefone inv�lido";
		}
		
		// PADRONIZA��O
		u.setEmail(u.getEmail().toLowerCase());
		u.setNome(u.getNome().toUpperCase());
		
		// REGRA DE NEG�CIO
		if(u.getCpf() == dao.consultarUsuarioCpf(u.getCpf()).getCpf()) {
			return "Cpf j� existe";
		}
		
		String mensagem = dao.gravarUsuario(u);
		dao.fechar();
		
		return mensagem;
	}
	
	/**
	 * M�todo respons�vel pelo:
	 *  Consultar o usuario se existe 
	 *  verificar se o usuario n�o � vazio
	 * 
	 * @param recebe o codigo int do usuario
	 * @return returna um objeto usuario
	 * @throws Exception valida a exce��o checked
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
	 * M�todo respons�vel pelo:
	 *  Consultar email e senha do usuario
	 *  Verificar se o usuario � vazio
	 * @param receber o objeto u do usu�rio
	 * @return retornar um objeto Usuario
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public static List<Usuario> buscarUsuarioNome(String nome) throws Exception {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		UsuarioDAO dao = new UsuarioDAO();
		
		// CONSULTAR USU�RIO PELO NOME
		listaUsuario = dao.consultarUsuarioNome(nome);
		dao.fechar();
		return listaUsuario;
	}

}
