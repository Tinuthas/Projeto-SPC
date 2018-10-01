package br.com.fiap.spc.bo;

import br.com.fiap.spc.beans.Funcionario;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.dao.FuncionarioDAO;
/**
 * Classe respons�vel por padronizar e controlar as regras de n�gocio do Funcionario
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.FuncionarioDAO
 * @see	br.com.fiap.spc.beans.Funcionario
 *
 */
public class FuncionarioBO {
	
	/**
	 * M�todo respons�vel pelo:
	 * 	Inserir maior codigo consultando o maior codigo
	 * 	Verificar a data o tamanho e se � vazia
	 * 	Verificar a fun��o o tamanho e se n�o � vazia
	 *  Padronizar fun��o deixando em caixa alta
	 *  Gravar Usuario
	 *  Gravar Pessoa
	 *  Gravar Funcionario
	 *  
	 * @param recebe um f objeto do Funcionario
	 * @return retorna uma String da classe FuncionarioDAO
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public static String novoFuncionario(Funcionario f) throws Exception {
		
		FuncionarioDAO dao = new FuncionarioDAO();
		
		// INSERINDO O MAIOR CODIGO DE FUNCIONARIO DE ACRESCIMO 1 DO ANTERIOR		
		int codigo = dao.maiorCodigo();
		if(codigo==0) {
			f.setCodigoUsuario(1);;
		}else {
			f.setCodigoUsuario(codigo + 1);
		}
		
		// VALIDA��O
		if(f.getDataAdmissao() == null ||f.getDataAdmissao().length() != 10) {
			return "Data inv�lida";
		}
		if(f.getFuncao() == null || f.getFuncao().length() > 30) {
			return "Fun��o inv�lida";
		}
				
		// PADRONIZA��O
		f.setFuncao(f.getFuncao().toUpperCase());
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date data = df.parse(f.getDataAdmissao());
//		df.applyPattern("dd/MM/yyyy");
//		f.setDataNascimento(df.format(data));
//		
		// GRAVANDO FUNCION�RIO
		String resposta = UsuarioBO.novoUsuario(f);
		if(!resposta.equals("Usu�rio Gravado")) {
			System.out.println(resposta);
			return resposta;
		}
		resposta = PessoaBO.novaPessoa(f);
		if(!(resposta.equals("Pessoa Gravado"))) {
			System.out.println(resposta);
			return resposta;
		}
	
		resposta = dao.gravarFuncionario(f);
		dao.fechar();
		
		return resposta;
	}
	
	/**
	 * M�todo respons�vel pelo:
	 *  Consultar o funcionario se existe 
	 *  verificar se o funcion�rio n�o � vazio
	 * 
	 * @param recebe o codigo int do funcion�rio
	 * @return returna um objeto funcion�rio
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	public static Funcionario buscarFuncionario(int codigo) throws Exception {
		
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f = new Funcionario();
		
		f = dao.consultar(codigo);
		dao.fechar();
		if(f.getCodigoUsuario()==0) {
			return null;
		}else {
			return f;
		}
		
		
	}
	
	/**
	 * M�todo respons�vel pelo:
	 *  Consultar email e senha do funcion�rio
	 *  Verificar se o funcionario � vazio
	 * @param receber o objeto f do funcion�rio
	 * @return retornar um objeto Usuario
	 * @throws Exception valida a exce��o checked
	 * @author Marcus V. Galdino
	 */
	
	
	public static Usuario verificarFuncionario(Funcionario f) throws Exception{
		
		FuncionarioDAO dao = new FuncionarioDAO();		
		f = (Funcionario) dao.consultarEmailSenha(f);
		
		//VERIFICAR O CODIGO DE USUARIO
		if(f.getCodigoUsuario() == 0) {
			return null;
		}
		
		
		dao.fechar();
		return f;
		
	}

}
