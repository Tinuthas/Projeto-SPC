package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.spc.beans.Endereco;
import br.com.fiap.spc.conexao.Conexao;
/**
* 
* Classe que manipula a tabela T_SPC_ENDERECO
* Vers�o 1.0 - vers�o inicial com m�todos CRUD
* 
* @author Marcus V. Galdino
* @version 1.0
* @since 1.0
* @see br.com.fiap.spc.beans.EnderecoBO
* @see br.com.fiap.spc.beans.Endereco
*
*/
public class EnderecoDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	/**
	 * M�todo construtor - abre conex�o com Banco
	 * 
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public EnderecoDAO() throws Exception{
		con = Conexao.conectar();
	}
	/**
	 * 
	 * M�todo de adicionar um novo endere�o na T_SPC_ENDERECO 
	 * Colunas obrigat�rios todas menos o bairro
	 * @param a recebe o objeto Endere�o do beans
	 * @return Retorna uma String com de mensagem do Endere�o registrado!
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public String gravar(Endereco ende) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SPC_ENDERECO (CD_ENDERECO, DS_LOGRADOURO, NR_ENDERECO,  NR_CEP, DS_UF, DS_CIDADE, DS_BAIRRO) "
				+ "VALUES (?,?,?,?,?,?,?)");
		stmt.setInt(1, ende.getCodigoEndereco()); 
		stmt.setString(2, ende.getLogradouro());
		stmt.setInt(3, ende.getNumeroEndereco());
		stmt.setInt(4, ende.getCep());
		stmt.setString(5, ende.getUf());
		stmt.setString(6, ende.getCidade());
		stmt.setString(7, ende.getBairro());
		stmt.executeUpdate();
		return "OK";
	}
	
	/**
	 * M�todo que pega o codigo do endereco e consulta se existe
	 * Pega as colunas do endereco
	 * @param codigo � um int do numero codigo do endereco
	 * @return Retorna um objeto Enderco que contem os dados do endereco
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 * 
	 */
	
	public Endereco consultarEndereco(int codigo) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SPC_ENDERECO WHERE CD_ENDERECO=?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		Endereco ende = new Endereco();
		if(rs.next()) {
			ende.setCodigoEndereco(rs.getInt("CD_ENDERECO"));
			ende.setLogradouro(rs.getString("DS_LOGRADOURO"));
			ende.setNumeroEndereco(rs.getInt("NR_ENDERECO"));
			ende.setCep(rs.getInt("NR_CEP"));
			ende.setUf(rs.getString("DS_UF"));
			ende.setCidade(rs.getString("DS_CIDADE"));
			ende.setBairro(rs.getString("DS_BAIRRO"));
		}
		return ende;
	}
	
	/**
	 * M�todo que pegar o maior codigo do endere�o
	 * Consulta as linhas do endere�o e pega o maior n�mero
	 * @param n�o recebe param�tros
	 * @return retorna um int com o codigo do maior endereco
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	
	public int maiorCodigo() throws Exception {
		stmt = con.prepareStatement("SELECT MAX(CD_ENDERECO) FROM T_SPC_ENDERECO");
		rs = stmt.executeQuery();
		rs.next();
		
		return rs.getInt("MAX(CD_ENDERECO)");
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
