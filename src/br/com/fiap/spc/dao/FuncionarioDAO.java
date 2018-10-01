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
* Vers�o 1.0 - vers�o inicial com m�todos CRUD
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
	 * M�todo construtor - abre conex�o com Banco
	 * 
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public FuncionarioDAO() throws Exception{
		con = Conexao.conectar();
	}
	/**
	 * 
	 * M�todo de adicionar um novo funcionario na T_SPC_FUNCIONARIO 
	 * Colunas obrigat�rios todas
	 * @param a recebe o objeto Funcion�rio do beans
	 * @return Retorna uma String com de mensagem do Funcionario registrado!
	 * @throws Exception (valida uma exce��o checked SQLException)
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
	 * M�todo que pega o codigo do funcion�rio e consulta se existe
	 * Pega a coluna codigo do funcion�rio
	 * @param codigo � um int do numero codigo do funcion�rio
	 * @return Retorna um objeto Funcionario que contem os dados do funcionario
	 * @throws Exception (valida uma exce��o checked SQLException)
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
	 * M�todo que pegar o maior codigo do usuario
	 * Consulta as linhas do funcion�rio e pega o maior n�mero
	 * @param n�o recebe param�tros
	 * @return retorna um int com o codigo do maior usu�rio
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public int maiorCodigo() throws Exception {
		stmt = con.prepareStatement("SELECT MAX(CD_USUARIO) FROM T_SPC_USUARIO");
		rs = stmt.executeQuery();
		rs.next();
		
		return rs.getInt("MAX(CD_USUARIO)");
	}
	
	/**
	 * M�todo para logar que consulta o email e senha do usu�rio
	 * Consulta o codigo do usu�rio
	 * @param retorna um objeto f que contem o email e senha
	 * @return retotna o codigo do usu�rio
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
	 * M�todo que fecha a conex�o
	 * @param n�o recebe param�tros
	 * @return returna nada
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public void fechar() throws Exception {
		con.close();
	}

}
