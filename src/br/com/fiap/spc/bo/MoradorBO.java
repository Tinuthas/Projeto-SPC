package br.com.fiap.spc.bo;

import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.dao.MoradorDAO;
/**
 * Classe responsável por padronizar e controlar as regras de négocio do Morador
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.MoradorDAO
 * @see	br.com.fiap.spc.beans.Morador
 *
 */
public class MoradorBO {
	/**
	 * Método responsável pelo:
	 * 	Inserir maior codigo consultando o maior codigo
	 * 	Verificar o apartamento o tamanho e se é vazia
	 * 	Verificar a torre o tamanho e se não é vazia
	 *  Padronizar torre deixando em caixa alta
	 *  Gravar Usuario
	 *  Gravar Pessoa
	 *  Gravar Morador
	 *  
	 * @param recebe um m objeto do Morador
	 * @return retorna uma String da classe MoradorDAO
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	public static String novoMorador(Morador m) throws Exception {
		
		MoradorDAO dao = new MoradorDAO();
		
		// INSERINDO O MAIOR CODIGO DE MORADOR DE ACRESCIMO 1 DO ANTERIOR
		int codigo = dao.maiorCodigo();
		if(codigo==0) {
			m.setCodigoUsuario(1);
		}else {
			m.setCodigoUsuario(codigo + 1);
		}
		// VALIDAÇÃO
		if(m.getNumeroApartamento() <= 0 || m.getNumeroApartamento() > 9999 ) {
			return "Apartamento inválido";
		}
		if(m.getTorre() == null || m.getTorre().length() > 3) {
			return "Torre inválida";
		}
		
		// PADRONIZAÇÃO
		m.setTorre(m.getTorre().toUpperCase());
		
		// GRAVANDO MORADOR 
		String resposta = UsuarioBO.novoUsuario(m);
		if(!resposta.equals("Usuário Gravado")) {
			System.out.println(resposta);
			return resposta;
		}
		resposta = PessoaBO.novaPessoa(m);
		if(!(resposta.equals("Pessoa Gravado"))) {
			System.out.println(resposta);
			return resposta;
		}
		resposta = dao.gravarMorador(m);System.out.println(resposta);
		dao.fechar();
		
		return resposta;
	}
	
	/**
	 * Método responsável pelo:
	 *  Consultar o morador se existe 
	 *  verificar se o morador não é vazio
	 * 
	 * @param recebe o codigo int do morador
	 * @return returna um objeto morador
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static Morador buscarMorador(int codigo) throws Exception {
		
		MoradorDAO dao = new MoradorDAO();
		Morador m = new Morador();
		
		m = dao.consultarMorador(codigo);
		dao.fechar();
		
		// VERIFICANDO CODIGO
		if(m.getCodigoUsuario()==0) {
			return null;
		}else {
			return m;
		}
		
		
	}

	/**
	 * Método responsável pelo:
	 *  Consultar email e senha do morador
	 *  Verificar se o morador é vazio
	 * @param receber o objeto m do morador
	 * @return retornar um objeto Usuario
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static Usuario verificarMorador(Morador m) throws Exception{
		
		MoradorDAO dao = new MoradorDAO();		
		m = (Morador) dao.consultarEmailSenha(m);
		
		//VERIFICAR O CODIGO DE USUARIO
		if(m.getCodigoUsuario() == 0) {
			return null;
		}			
		dao.fechar();
		return m;
		
	}
	
	

}
