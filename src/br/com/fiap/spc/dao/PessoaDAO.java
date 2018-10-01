package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.spc.beans.Pessoa;
import br.com.fiap.spc.conexao.Conexao;
/**
* 
* Classe que manipula a tabela T_SPC_PESSOA
* Vers�o 1.0 - vers�o inicial com m�todos CRUD
* 
* @author Marcus V. Galdino
* @version 1.0
* @since 1.0
* @see br.com.fiap.spc.beans.PessoaBO
* @see br.com.fiap.spc.beans.Pessoa
*
*/
public class PessoaDAO extends UsuarioDAO{
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	/**
	 * M�todo construtor - abre conex�o com Banco
	 * 
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public PessoaDAO() throws Exception {
		con = Conexao.conectar();
	}
	/**
	 * 
	 * M�todo de adicionar uma nova pessoa na T_SPC_PESSOA 
	 * Colunas obrigat�rios todas
	 * @param a recebe o objeto Pessoa do beans
	 * @return Retorna uma String com de mensagem do Pessoa Gravada!
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public String gravarPessoa(Pessoa p) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SPC_PESSOA (CD_USUARIO, CD_CONDOMINIO, NR_RG, NR_RG_DIG, DT_NASCIMENTO, DS_SEXO) "
				+ "VALUES (?,?,?,?,?,?)");
		stmt.setInt(1, p.getCodigoUsuario());
		stmt.setInt(2, p.getCondominio().getCodigoCondominio());
		stmt.setInt(3, p.getRg());
		stmt.setString(4, p.getRgDig());
		stmt.setString(5, p.getDataNascimento());
		stmt.setString(6, p.getSexo());
		
		stmt.executeUpdate();
		
		return "Pessoa Gravado";
	}
	
	/**
	 * M�todo que pega o codigo da pessoa e consulta se existe
	 * Pega a coluna codigo do pessoa
	 * @param codigo � um int do numero codigo do pessoa
	 * @return Retorna um objeto Pessoa que contem os dados do pessoa
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 * 
	 */
	
	public Pessoa consultar(int codigo) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SPC_PESSOA WHERE CD_USUARIO=?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		Pessoa p = new Pessoa();
		if(rs.next()) {
			p.setCodigoUsuario(rs.getInt("CD_USUARIO"));

		}
		
		return p;
	}
	
	/**
	 * M�todo que fecha a conex�o
	 * @param n�o recebe param�tros
	 * @return returna nada
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public void fechar() throws Exception{
		con.close();
	}
	

}
