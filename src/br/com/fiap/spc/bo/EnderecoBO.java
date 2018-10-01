package br.com.fiap.spc.bo;

import br.com.fiap.spc.beans.Endereco;
import br.com.fiap.spc.dao.EnderecoDAO;
/**
 * Classe responsável por padronizar e controlar as regras de négocio do Endereco
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.EnderecoDAO
 * @see	br.com.fiap.spc.beans.Endereco
 *
 */
public class EnderecoBO {
	
	/**
	 * Método Responsável pelo:
	 *  Validar o logradouro do endereço tamanho e se é vazia
	 *  Validar o número de endereço se encaixa no tamanho
	 *  Validar o bairro se o tamanho
	 *  Validar a cidade o tamanho e se não é vazia
	 *  Validar a uf o tamanho e se não é vazia
	 *  Validar o cep se ele encaixa no tamanho
	 *  Inserir o maior codigo pela busca do maior
	 *  Deixar logradouro,  Bairro, Cidade e Uf em caixa alta
	 *  Gravar endereço
	 *  
	 * @param recebe um objeto ende do Endereco
	 * @return returna uma String da classe EnderecoDAO
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static String novoEndereco(Endereco ende) throws Exception {
		
		EnderecoDAO dao = new EnderecoDAO();
		
		// VALIDAÇÃO 
		if(ende.getLogradouro() == null || ende.getLogradouro().length() > 100) {
			return "Logradouro inválido!";
		}
		
		if(ende.getNumeroEndereco() <= 0 || ende.getNumeroEndereco() > 99999) {
			return "Número inválido!";
		}	
		
		if(ende.getBairro().length() > 30) {
			return "Bairro inválido!";
		}
		if(ende.getCidade() == null || ende.getCidade().length() > 30) {
			return "Cidade inválido!";
		}
		if(ende.getUf() == null || ende.getUf().length() > 2) {
			return "Uf inválido!";
		}
		if(ende.getCep() < 10000000 || ende.getCep() > 99999999) {
			return "Cep inválido";
		}
		
		// REGRA DE NEGÓCIO - INSERIR MAIOR NÚMERO
		
		int codigo = dao.maiorCodigo();
		if(codigo==0) {
			ende.setCodigoEndereco(1);
		}else {
			ende.setCodigoEndereco(codigo + 1);
		}
		
		// PADRONIZAÇÃO
		ende.setLogradouro(ende.getLogradouro().toUpperCase());
		ende.setBairro(ende.getBairro().toUpperCase());
		ende.setCidade(ende.getCidade().toUpperCase());
		ende.setUf(ende.getUf().toUpperCase());
		
		String mensagem = dao.gravar(ende);
		dao.fechar();
		
		return mensagem;
	}
	
	/**
	 * Método responsável pelo:
	 *  Consultar o endereço
	 *  Verificar o resultado consultado
	 * 
	 * @param recebe codigo int do endereco
	 * @return retorna o objeto Endereco
	 * @throws Exception valida e exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public Endereco buscarEndereco(int codigo) throws Exception {
		EnderecoDAO dao = new EnderecoDAO();
		Endereco ende = dao.consultarEndereco(codigo);
		// VERIFICAR BUSCA DO ENDEREÇO
		dao.fechar();
		if(ende.getCodigoEndereco() == 0) {
			return null;
		}else {
			return ende;
		}
		
	}

}
