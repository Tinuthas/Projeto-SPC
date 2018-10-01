package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.conexao.Conexao;
/**
* 
* Classe que manipula a tabela T_SPC_MORADOR
* Versão 1.0 - versão inicial com métodos CRUD
* 
* @author Marcus V. Galdino
* @version 1.0
* @since 1.0
* @see br.com.fiap.spc.beans.MoradorBO
* @see br.com.fiap.spc.beans.Morador
*
*/
public class MoradorDAO extends PessoaDAO{
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 * Método construtor - abre conexão com Banco
	 * 
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public MoradorDAO() throws Exception {
		con = Conexao.conectar();
	}
	/**
	 * 
	 * Método de adicionar um novo morador na T_SPC_MORADOR 
	 * Colunas obrigatórios todas
	 * @param a recebe o objeto Morador do beans
	 * @return Retorna uma String com de mensagem do Morador registrado!
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public String gravarMorador(Morador m) throws Exception {
		
		
		stmt = con.prepareStatement("INSERT INTO T_SPC_MORADOR (CD_MORADOR, NR_APARTAMENTO, DS_TORRE)"
				+ "VALUES(?,?,?)");
		stmt.setInt(1, m.getCodigoUsuario());
		stmt.setInt(2, m.getNumeroApartamento());
		stmt.setString(3, m.getTorre());
		stmt.executeUpdate();
		
		return "Morador registrado";
	}
	
	/**
	 * Método que pega o codigo do morador e consulta se existe
	 * Pega a coluna codigo do morador
	 * @param codigo é um int do numero codigo do morador
	 * @return Retorna um objeto Morador que contem os dados do morador
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 * 
	 */
	
	public Morador consultarMorador(int codigo) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SPC_MORADOR WHERE CD_MORADOR=?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		
		Morador m = new Morador();
		if(rs.next()) {
			m.setCodigoUsuario(rs.getInt("CD_MORADOR"));
		}
		
		return m;
	}
	
	/**
	 * Método para logar que consulta o email e senha do usuário
	 * Consulta o codigo do usuário
	 * @param retorna um objeto f que contem o email e senha
	 * @return retotna o codigo do usuário
	 * @throws Exception
	 * @author Marcus V. Galdino
	 */
	
	public Usuario consultarEmailSenha(Morador m) throws Exception {
		
		stmt = con.prepareStatement("SELECT U.CD_USUARIO, U.DS_EMAIL, U.DS_SENHA FROM T_SPC_USUARIO U INNER JOIN T_SPC_MORADOR M ON (U.CD_USUARIO = M.CD_MORADOR) WHERE U.DS_EMAIL=? AND U.DS_SENHA=?");
		stmt.setString(1, m.getEmail());
		stmt.setString(2, m.getSenha());
		rs = stmt.executeQuery();
		if(rs.next()) {
			m.setCodigoUsuario(rs.getInt("CD_USUARIO"));
			m.setEmail(rs.getString("DS_EMAIL"));
			m.setSenha(rs.getString("DS_SENHA"));
		}
		
		return m;
		
		
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
