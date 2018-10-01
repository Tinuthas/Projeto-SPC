package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.spc.beans.Funcionario;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.conexao.Conexao;
/**
* 
* Classe que manipula a tabela T_SPC_FUNCIONARIO
* Versão 1.0 - versão inicial com métodos CRUD
* 
* @author Marcus V. Galdino
* @version 1.0
* @since 1.0
* @see br.com.fiap.spc.beans.FuncionarioBO
* @see br.com.fiap.spc.beans.Funcionario
*
*/
public class FuncionarioDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	/**
	 * Método construtor - abre conexão com Banco
	 * 
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public FuncionarioDAO() throws Exception{
		con = Conexao.conectar();
	}
	/**
	 * 
	 * Método de adicionar um novo funcionario na T_SPC_FUNCIONARIO 
	 * Colunas obrigatórios todas
	 * @param a recebe o objeto Funcionário do beans
	 * @return Retorna uma String com de mensagem do Funcionario registrado!
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public String gravarFuncionario(Funcionario f) throws Exception {		
		stmt = con.prepareStatement("INSERT INTO T_SPC_FUNCIONARIO (CD_FUNCIONARIO, DT_ADMISSAO, DS_FUNCAO) "
				+ "VALUES (?,?,?)");
		stmt.setInt(1, f.getCodigoUsuario());
		stmt.setString(2, f.getDataAdmissao());
		stmt.setString(3, f.getFuncao());
		stmt.executeUpdate();
		
		return "Funcionario registrado!";
	}
	
	/**
	 * Método que pega o codigo do funcionário e consulta se existe
	 * Pega a coluna codigo do funcionário
	 * @param codigo é um int do numero codigo do funcionário
	 * @return Retorna um objeto Funcionario que contem os dados do funcionario
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 * 
	 */
	
	public Funcionario consultar(int codigo) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SPC_FUNCIONARIO WHERE CD_FUNCIONARIO=?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		Funcionario f = new Funcionario();
		if(rs.next()) {
			f.setCodigoUsuario(rs.getInt("CD_FUNCIONARIO"));
		}
			
		
		return f;
	}
	/**
	 * Método que pegar o maior codigo do usuario
	 * Consulta as linhas do funcionário e pega o maior número
	 * @param não recebe paramêtros
	 * @return retorna um int com o codigo do maior usuário
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public int maiorCodigo() throws Exception {
		stmt = con.prepareStatement("SELECT MAX(CD_USUARIO) FROM T_SPC_USUARIO");
		rs = stmt.executeQuery();
		rs.next();
		
		return rs.getInt("MAX(CD_USUARIO)");
	}
	
	/**
	 * Método para logar que consulta o email e senha do usuário
	 * Consulta o codigo do usuário
	 * @param retorna um objeto f que contem o email e senha
	 * @return retotna o codigo do usuário
	 * @throws Exception
	 * @author Marcus V. Galdino
	 */
	
	public Usuario consultarEmailSenha(Funcionario f) throws Exception {
		
		stmt = con.prepareStatement("SELECT U.CD_USUARIO FROM T_SPC_USUARIO U INNER JOIN T_SPC_FUNCIONARIO F ON (U.CD_USUARIO =  F.CD_FUNCIONARIO) WHERE U.DS_EMAIL=? AND U.DS_SENHA=?");
		stmt.setString(1, f.getEmail());
		stmt.setString(2, f.getSenha());
		rs = stmt.executeQuery();
		if(rs.next()) {
			f.setCodigoUsuario(rs.getInt("CD_USUARIO"));
		}
		
		return f;
		
		
	}
	
	/**
	 * Método que fecha a conexão
	 * @param não recebe paramêtros
	 * @return returna nada
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public void fechar() throws Exception {
		con.close();
	}

}
