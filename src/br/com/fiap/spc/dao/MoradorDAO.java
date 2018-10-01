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
* Vers�o 1.0 - vers�o inicial com m�todos CRUD
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
	 * M�todo construtor - abre conex�o com Banco
	 * 
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public MoradorDAO() throws Exception {
		con = Conexao.conectar();
	}
	/**
	 * 
	 * M�todo de adicionar um novo morador na T_SPC_MORADOR 
	 * Colunas obrigat�rios todas
	 * @param a recebe o objeto Morador do beans
	 * @return Retorna uma String com de mensagem do Morador registrado!
	 * @throws Exception (valida uma exce��o checked SQLException)
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
	 * M�todo que pega o codigo do morador e consulta se existe
	 * Pega a coluna codigo do morador
	 * @param codigo � um int do numero codigo do morador
	 * @return Retorna um objeto Morador que contem os dados do morador
	 * @throws Exception (valida uma exce��o checked SQLException)
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
	 * M�todo para logar que consulta o email e senha do usu�rio
	 * Consulta o codigo do usu�rio
	 * @param retorna um objeto f que contem o email e senha
	 * @return retotna o codigo do usu�rio
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
