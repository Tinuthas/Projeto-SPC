package br.com.fiap.spc.bo;

import br.com.fiap.spc.beans.Funcionario;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.dao.FuncionarioDAO;
/**
 * Classe responsável por padronizar e controlar as regras de négocio do Funcionario
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.dao.FuncionarioDAO
 * @see	br.com.fiap.spc.beans.Funcionario
 *
 */
public class FuncionarioBO {
	
	/**
	 * Método responsável pelo:
	 * 	Inserir maior codigo consultando o maior codigo
	 * 	Verificar a data o tamanho e se é vazia
	 * 	Verificar a função o tamanho e se não é vazia
	 *  Padronizar função deixando em caixa alta
	 *  Gravar Usuario
	 *  Gravar Pessoa
	 *  Gravar Funcionario
	 *  
	 * @param recebe um f objeto do Funcionario
	 * @return retorna uma String da classe FuncionarioDAO
	 * @throws Exception valida a exceção checked
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
		
		// VALIDAÇÃO
		if(f.getDataAdmissao() == null ||f.getDataAdmissao().length() != 10) {
			return "Data inválida";
		}
		if(f.getFuncao() == null || f.getFuncao().length() > 30) {
			return "Função inválida";
		}
				
		// PADRONIZAÇÃO
		f.setFuncao(f.getFuncao().toUpperCase());
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date data = df.parse(f.getDataAdmissao());
//		df.applyPattern("dd/MM/yyyy");
//		f.setDataNascimento(df.format(data));
//		
		// GRAVANDO FUNCIONÁRIO
		String resposta = UsuarioBO.novoUsuario(f);
		if(!resposta.equals("Usuário Gravado")) {
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
	 * Método responsável pelo:
	 *  Consultar o funcionario se existe 
	 *  verificar se o funcionário não é vazio
	 * 
	 * @param recebe o codigo int do funcionário
	 * @return returna um objeto funcionário
	 * @throws Exception valida a exceção checked
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
	 * Método responsável pelo:
	 *  Consultar email e senha do funcionário
	 *  Verificar se o funcionario é vazio
	 * @param receber o objeto f do funcionário
	 * @return retornar um objeto Usuario
	 * @throws Exception valida a exceção checked
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
