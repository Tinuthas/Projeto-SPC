package br.com.fiap.spc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Vinculo;
import br.com.fiap.spc.dao.VinculoDAO;
/**
 * Classe respons�vel por padronizar e controlar as regras de n�gocio do Vinculo
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.VinculoDAO
 * @see	br.com.fiap.spc.beans.Vinculo
 *
 */
public class VinculoBO {
	
	/**
	 * M�todo respons�vel pelo:
	 * 	Verificar logradouro se � vazio e o tamanho
	 *  Padronizar o logradouro em caixa alta
	 *  Gravar pessoa
	 *  Gravar Vinculo
	 * 
	 * @param recebe o objeto v do Vinculo
	 * @return returna uma String da classe VinculoDAO
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public static String novoVinculo(Vinculo v) throws Exception {
		VinculoDAO dao = new VinculoDAO();
		
		// VERIFICA��O
		if(v.getLogradouro() == null || v.getLogradouro().length() > 100) {
			return "Logradouro inv�lido";
		}
		
		// PADRONIZA��O
		v.setLogradouro(v.getLogradouro().toUpperCase());
		String mensagem = UsuarioBO.novoUsuario(v);
		if(mensagem != "Usu�rio Gravado") {
			return mensagem;
		}
		
		mensagem = dao.gravar(v);
		dao.fechar();
		return mensagem;
	}
	
	/**
	 * M�todo respons�vel pelo:
	 *  Consulta a lista de vinculos do morador
	 *  Verificar se a lista � vazia
	 * 
	 * @param recebe codigo int do morador vinculado
	 * @return retorna lista de vinculos do morador
	 * @throws Exception valida a exce��o checked
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
