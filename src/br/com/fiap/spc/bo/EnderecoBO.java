package br.com.fiap.spc.bo;

import br.com.fiap.spc.beans.Endereco;
import br.com.fiap.spc.dao.EnderecoDAO;
/**
 * Classe respons�vel por padronizar e controlar as regras de n�gocio do Endereco
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.EnderecoDAO
 * @see	br.com.fiap.spc.beans.Endereco
 *
 */
public class EnderecoBO {
	
	/**
	 * M�todo Respons�vel pelo:
	 *  Validar o logradouro do endere�o tamanho e se � vazia
	 *  Validar o n�mero de endere�o se encaixa no tamanho
	 *  Validar o bairro se o tamanho
	 *  Validar a cidade o tamanho e se n�o � vazia
	 *  Validar a uf o tamanho e se n�o � vazia
	 *  Validar o cep se ele encaixa no tamanho
	 *  Inserir o maior codigo pela busca do maior
	 *  Deixar logradouro,  Bairro, Cidade e Uf em caixa alta
	 *  Gravar endere�o
	 *  
	 * @param recebe um objeto ende do Endereco
	 * @return returna uma String da classe EnderecoDAO
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public static String novoEndereco(Endereco ende) throws Exception {
		
		EnderecoDAO dao = new EnderecoDAO();
		
		// VALIDA��O 
		if(ende.getLogradouro() == null || ende.getLogradouro().length() > 100) {
			return "Logradouro inv�lido!";
		}
		
		if(ende.getNumeroEndereco() <= 0 || ende.getNumeroEndereco() > 99999) {
			return "N�mero inv�lido!";
		}	
		
		if(ende.getBairro().length() > 30) {
			return "Bairro inv�lido!";
		}
		if(ende.getCidade() == null || ende.getCidade().length() > 30) {
			return "Cidade inv�lido!";
		}
		if(ende.getUf() == null || ende.getUf().length() > 2) {
			return "Uf inv�lido!";
		}
		if(ende.getCep() < 10000000 || ende.getCep() > 99999999) {
			return "Cep inv�lido";
		}
		
		// REGRA DE NEG�CIO - INSERIR MAIOR N�MERO
		
		int codigo = dao.maiorCodigo();
		if(codigo==0) {
			ende.setCodigoEndereco(1);
		}else {
			ende.setCodigoEndereco(codigo + 1);
		}
		
		// PADRONIZA��O
		ende.setLogradouro(ende.getLogradouro().toUpperCase());
		ende.setBairro(ende.getBairro().toUpperCase());
		ende.setCidade(ende.getCidade().toUpperCase());
		ende.setUf(ende.getUf().toUpperCase());
		
		String mensagem = dao.gravar(ende);
		dao.fechar();
		
		return mensagem;
	}
	
	/**
	 * M�todo respons�vel pelo:
	 *  Consultar o endere�o
	 *  Verificar o resultado consultado
	 * 
	 * @param recebe codigo int do endereco
	 * @return retorna o objeto Endereco
	 * @throws Exception valida e exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public Endereco buscarEndereco(int codigo) throws Exception {
		EnderecoDAO dao = new EnderecoDAO();
		Endereco ende = dao.consultarEndereco(codigo);
		// VERIFICAR BUSCA DO ENDERE�O
		dao.fechar();
		if(ende.getCodigoEndereco() == 0) {
			return null;
		}else {
			return ende;
		}
		
	}

}
