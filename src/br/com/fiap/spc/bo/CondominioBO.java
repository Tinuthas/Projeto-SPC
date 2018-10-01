package br.com.fiap.spc.bo;

/**
 * Classe responsável por padronizar e controlar as regras de négocio do Condominio
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.CondominioDAO
 * @see	br.com.fiap.spc.beans.Condominio
 *
 */
import br.com.fiap.spc.beans.Condominio;
import br.com.fiap.spc.dao.CondominioDAO;

public class CondominioBO {
	/**
	 * Método Responsável pelo:
	 * 	Verificar se a razão social não existe
	 *  Verificar o tamanho do nome da razão e se não é null
	 * 	Verificar se o cnpj se encaixa no tamanho 
	 * 	Verificar se o numero de telefone se encaixa no seu tamanho	
	 * 	Verificar se email é maior que 80
	 * 	Inserir o maior código
	 * 	Verificar tipo de condomínio
	 *  Gravar endereço e verificar
	 *  Gravar condominio
	 * 	
	 * @param recebe cond um objeto Condominio
	 * @return retorna uma String da classe CondominioDAO
	 * @throws Exception valida a exceção checked
	 * @author Marcus V. Galdino
	 */
	public static String novoCondominio(Condominio cond) throws Exception {
		CondominioDAO dao = new CondominioDAO();
		
		//VALIDAÇÃO
		if(cond.getRazaoSocial().equals(dao.consultarCondRazaoSocial(cond.getRazaoSocial()).getRazaoSocial())) {
			return "Razão Social já existe!";
		}
		if(cond.getRazaoSocial().length() > 60 ||cond.getRazaoSocial().equals(null)) {
			return "Razão Social inválido!";
		}
		if(cond.getCnpj() < 1000000000 || cond.getCnpj() > 99999999999999L) {
			return "Cnpf Inválido";
		}
		if(cond.getTelefone()< 10000000 || cond.getTelefone() > 9999999999999L) {
			return "Telefone Inválido";
		}
		if(cond.getEmail().length() > 80) {
			return "Email inválido";
		}
		
		//REGRA DE NEGOCIO - MAIOR CODIGO
		int codigo = dao.maiorCodigo();
		if(codigo==0) {
			cond.setCodigoCondominio(1);
		}else {
			cond.setCodigoCondominio(codigo + 1);
		}
		
		//REGRA DE NEGOCIO - TIPO CONDOMINIO
		if(cond.getDs_tipo_condominio() < 1 || cond.getDs_tipo_condominio() > 2) {
			return "Tipo inválido";
		}
		
		//VALIDAÇÃO DO ENDERECO
		String mensEndereco = EnderecoBO.novoEndereco(cond.getEndereco());
		if(!mensEndereco.equals("OK")) {
			return mensEndereco;
		}
		
		String mensagem = dao.gravar(cond);
		dao.fechar();
		
		return mensagem;
	}
	
	/**
	 * Método responsável pelo: 
	 * 	Consultar condominio pelo codigo
	 *  Verificar se o condominio não é null
	 * 
	 * @param recebe o codigo int do codigo do condominio
	 * @return return o objeto condominio
	 * @throws Exception validar a exceção checked
	 * @author Marcus V. Galdino
	 */
	
	public static Condominio buscarCondominio(int codigo) throws Exception{
		
		CondominioDAO dao = new CondominioDAO();
		Condominio cond = new Condominio();
		
		cond = dao.consultarCondominio(codigo);
		dao.fechar();
		
		//VERIFICANDO CODIGO
		if(cond.getCodigoCondominio() == 0) {
			return null;
		}else {
			return cond;
		}
	
	}

}
