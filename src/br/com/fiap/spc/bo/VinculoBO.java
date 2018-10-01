package br.com.fiap.spc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Vinculo;
import br.com.fiap.spc.dao.VinculoDAO;
/**
 * Classe responsável por padronizar e controlar as regras de négocio do Vinculo
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.VinculoDAO
 * @see	br.com.fiap.spc.beans.Vinculo
 *
 */
public class VinculoBO {
	
	/**
	 * Método responsável pelo:
	 * 	Verificar logradouro se é vazio e o tamanho
	 *  Padronizar o logradouro em caixa alta
	 *  Gravar pessoa
	 *  Gravar Vinculo
	 * 
	 * @param recebe o objeto v do Vinculo
	 * @return returna uma String da classe VinculoDAO
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static String novoVinculo(Vinculo v) throws Exception {
		VinculoDAO dao = new VinculoDAO();
		
		// VERIFICAÇÃO
		if(v.getLogradouro() == null || v.getLogradouro().length() > 100) {
			return "Logradouro inválido";
		}
		
		// PADRONIZAÇÃO
		v.setLogradouro(v.getLogradouro().toUpperCase());
		String mensagem = UsuarioBO.novoUsuario(v);
		if(mensagem != "Usuário Gravado") {
			return mensagem;
		}
		
		mensagem = dao.gravar(v);
		dao.fechar();
		return mensagem;
	}
	
	/**
	 * Método responsável pelo:
	 *  Consulta a lista de vinculos do morador
	 *  Verificar se a lista é vazia
	 * 
	 * @param recebe codigo int do morador vinculado
	 * @return retorna lista de vinculos do morador
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static List<Vinculo> buscarVinculo(int codigo) throws Exception {
		List<Vinculo> listaVinculo = new ArrayList<Vinculo>();
		VinculoDAO dao = new VinculoDAO();
		
		if(codigo > 0) {
			listaVinculo = dao.consultarVinculo(codigo);
		}
		dao.fechar();
		return listaVinculo;
	}
	
}
