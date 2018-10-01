package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Vinculo;
import br.com.fiap.spc.conexao.Conexao;
/**
* 
* Classe que manipula a tabela T_SPC_VINCULO
* Vers�o 1.0 - vers�o inicial com m�todos CRUD
* 
* @author Marcus V. Galdino
* @version 1.0
* @since 1.0
* @see br.com.fiap.spc.beans.VinculoBO
* @see br.com.fiap.spc.beans.Vinculo
*
*/
public class VinculoDAO extends UsuarioDAO{
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 * M�todo construtor - abre conex�o com Banco
	 * 
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public VinculoDAO() throws Exception {
		con = Conexao.conectar();
	}
	
	/**
	 * 
	 * M�todo de adicionar um novo vinculo na T_SPC_VINCULO e guardar os dados
	 * Colunas obrigat�rias
	 * @param a recebe o objeto Comunicado do beans
	 * @return Retorna uma String com de mensagem do Comunicado enviado!
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public String gravar(Vinculo v) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SPC_VINCULO (CD_MORADOR,CD_VINCULO) "
				+ "VALUES (?,?,?)");
		stmt.setInt(1, v.getMorador().getCodigoUsuario());
		stmt.setInt(2, v.getCodigoUsuario());
		stmt.setString(3, v.getLogradouro());
		stmt.executeUpdate();
		return "Vinculo Gravado";
	}
	
	/**
	 * M�todo que consulta a lista de vinculos do c�digo de usu�rio
	 * Pega um codigo que consultara os dados do vinculo
	 * @param recebe um codigo do usu�rio para pegar todos os vinculos
	 * @return retorna uma lista dos vinculos naquele usu�rio
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public List<Vinculo> consultarVinculo( int codigo) throws Exception {
		List<Vinculo> listaVinculo = new ArrayList<Vinculo>();
		Vinculo v = new Vinculo();
		stmt = con.prepareStatement("SELECT V.CD_VINCULO, U.NM_NOME, U.NR_TELEFONE FROM T_SPC_VINCULO V INNER JOIN T_SPC_MORADOR M ON (V.CD_MORADOR = M.CD_MORADOR) INNER JOIN T_SPC_USUARIO U ON(U.CD_USUARIO = V.CD_VINCULO) WHERE M.CD_MORADOR=?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		while(rs.next()) {
			v.setCodigoUsuario(rs.getInt("V.CD_VINCULO"));
			v.setNome(rs.getString("NM_NOME"));
			v.setTelefone(rs.getLong("NR_TELEFONE"));
			listaVinculo.add(v);
		}
		
		return listaVinculo;
		
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
