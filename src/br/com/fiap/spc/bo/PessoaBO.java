package br.com.fiap.spc.bo;

import br.com.fiap.spc.beans.Pessoa;
import br.com.fiap.spc.dao.PessoaDAO;
/**
 * Classe responsável por padronizar e controlar as regras de négocio do Pessoa
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.PessoaDAO
 * @see	br.com.fiap.spc.beans.Pessoa
 *
 */

public class PessoaBO {
	
	/**
	 * Método responsável pelo: 
	 * Valida Rg o tamanho se encaixa
	 * Valida o Dig RG o tamanho
	 * Valida a data de nascimento se é vazia e o tamanho
	 * Verificar se o codigo do condominio existe
	 * Gravar Pessoa
	 * 
	 * @param recebe objeto p da Pessoa
	 * @return retorna uma String da classe PessoaDAO
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static String novaPessoa(Pessoa p) throws Exception {
		PessoaDAO dao = new PessoaDAO();
		
		
		// VALIDAÇÃO 
		if(p.getRg() < 1000000 || p.getRg() > 999999999) {
			return "Rg inválido";
		}
		if(p.getRgDig().length()> 2) {
			return "Digito inválido";
		}
		if(p.getDataNascimento() == null|| p.getDataNascimento().length()!=10) {
			return "Data Inválida";
		}
		if(p.getSexo().length() > 2) {
			return "Sexo inválido";
		}
		if(p.getCondominio().getCodigoCondominio() != CondominioBO.buscarCondominio(p.getCondominio().getCodigoCondominio()).getCodigoCondominio()) {
			return "Condominio não existe";
		}
		
		// PADRONIZAÇÃO
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date data = df.parse(p.getDataNascimento());
//		df.applyPattern("dd/MM/yyyy");
//		p.setDataNascimento(df.format(data));
//		
		
		
		String mensagem = dao.gravarPessoa(p);
		dao.fechar();
		return mensagem;
	}
	
	/**
	 * Método responsável pelo: 
	 *  Consultar a pessoa se existe
	 *  Verificar se o resultado recebido existe
	 *  
	 * @param recebe o codigo int da pessoa
	 * @return returna um objeto Pessoa
	 * @throws Exception valida a exceção checked
	 */
	public static Pessoa buscarPessoa(int codigo) throws Exception {
		
		PessoaDAO dao = new PessoaDAO();
		Pessoa p = new Pessoa();
		
		p = dao.consultar(codigo);
		dao.fechar();
		
		if(p.getCodigoUsuario() == 0) {
			return null;
		}else  {
			return p;
		}
		
		
	}

}
