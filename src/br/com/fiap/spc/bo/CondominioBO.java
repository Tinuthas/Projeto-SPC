package br.com.fiap.spc.bo;

/**
 * Classe respons�vel por padronizar e controlar as regras de n�gocio do Condominio
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
	 * M�todo Respons�vel pelo:
	 * 	Verificar se a raz�o social n�o existe
	 *  Verificar o tamanho do nome da raz�o e se n�o � null
	 * 	Verificar se o cnpj se encaixa no tamanho 
	 * 	Verificar se o numero de telefone se encaixa no seu tamanho	
	 * 	Verificar se email � maior que 80
	 * 	Inserir o maior c�digo
	 * 	Verificar tipo de condom�nio
	 *  Gravar endere�o e verificar
	 *  Gravar condominio
	 * 	
	 * @param recebe cond um objeto Condominio
	 * @return retorna uma String da classe CondominioDAO
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	public static String novoCondominio(Condominio cond) throws Exception {
		CondominioDAO dao = new CondominioDAO();
		
		//VALIDA��O
		if(cond.getRazaoSocial().equals(dao.consultarCondRazaoSocial(cond.getRazaoSocial()).getRazaoSocial())) {
			return "Raz�o Social j� existe!";
		}
		if(cond.getRazaoSocial().length() > 60 ||cond.getRazaoSocial().equals(null)) {
			return "Raz�o Social inv�lido!";
		}
		if(cond.getCnpj() < 1000000000 || cond.getCnpj() > 99999999999999L) {
			return "Cnpf Inv�lido";
		}
		if(cond.getTelefone()< 10000000 || cond.getTelefone() > 9999999999999L) {
			return "Telefone Inv�lido";
		}
		if(cond.getEmail().length() > 80) {
			return "Email inv�lido";
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
			return "Tipo inv�lido";
		}
		
		//VALIDA��O DO ENDERECO
		String mensEndereco = EnderecoBO.novoEndereco(cond.getEndereco());
		if(!mensEndereco.equals("OK")) {
			return mensEndereco;
		}
		
		String mensagem = dao.gravar(cond);
		dao.fechar();
		
		return mensagem;
	}
	
	/**
	 * M�todo respons�vel pelo: 
	 * 	Consultar condominio pelo codigo
	 *  Verificar se o condominio n�o � null
	 * 
	 * @param recebe o codigo int do codigo do condominio
	 * @return return o objeto condominio
	 * @throws Exception validar a exce��o checked
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
