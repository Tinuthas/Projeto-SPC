package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.conexao.Conexao;
/**
* 
* Classe que manipula a tabela T_SPC_USUARIO
* Versão 1.0 - versão inicial com métodos CRUD
* 
* @author Marcus V. Galdino
* @version 1.0
* @since 1.0
* @see br.com.fiap.spc.beans.UsuarioBO
* @see br.com.fiap.spc.beans.Usuario
*
*/
public class UsuarioDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	/**
	 * Método construtor - abre conexão com Banco
	 * 
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public UsuarioDAO() throws Exception {
		con = Conexao.conectar();
	}
	/**
	 * 
	 * Método de adicionar um novo usuário na T_SPC_USUARIO e guardar os dados
	 * Todas as colunas obrigatórias menos o telefone
	 * @param a recebe o objeto Usuario do beans
	 * @return Retorna uma String com de mensagem do Usuario gravado!
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public String gravarUsuario(Usuario u) throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_SPC_USUARIO (CD_USUARIO, DS_SENHA, NM_NOME, NR_CPF, DS_EMAIL, NR_TELEFONE) "
				+ "VALUES (?,?,?,?,?,?)");
		stmt.setInt(1, u.getCodigoUsuario());
		stmt.setString(2, u.getSenha());
		stmt.setString(3, u.getNome());
		stmt.setLong(4, u.getCpf());
		stmt.setString(5, u.getEmail());
		stmt.setLong(6, u.getTelefone());
		stmt.executeUpdate();
		
		return "Usuário Gravado";
	}
	
	/**
	 * Método que pega o codigo do usuario e consulta se existe
	 * Pega a coluna codigo do usuário
	 * @param codigo é um int do numero codigo do usuário
	 * @return Retorna um objeto Usuário que contem os dados do usuário
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 * 
	 */
	
	public Usuario consultarUsuario(int codigo) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SPC_USUARIO WHERE CD_USUARIO=?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		Usuario u = new Usuario();
		if(rs.next()) {
			u.setCodigoUsuario(rs.getInt("CD_USUARIO"));
			u.setNome(rs.getString("NM_NOME"));
			u.setCpf(rs.getLong("NR_CPF"));
			u.setEmail(rs.getString("DS_EMAIL"));
			u.setTelefone(rs.getLong("DS_TELEFONE"));
		}
		
		return u;
	}
	
	/**
	 * Método que pega o codigo do usuario e consulta se existe usuando o cpf
	 * Pega a coluna do cpf
	 * @param cpf é um long do cpf do usuario
	 * @return Retorna um objeto Usuário que contem os dados do usuário com seu cpf
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 * 
	 */
	
	public Usuario consultarUsuarioCpf(long cpf) throws Exception {
		stmt = con.prepareStatement("SELECT NR_CPF FROM T_SPC_USUARIO WHERE NR_CPF=?");
		stmt.setLong(1, cpf);
		rs = stmt.executeQuery();
		Usuario u = new Usuario();
		if(rs.next()) {
			u.setCpf(rs.getLong("NR_CPF"));
		}
		
		return u;
	}
	
	/**
	 * Método que consulta a lista de usuarios pelo nome
	 * Pega um codigo que consultara todos os dados do usuário
	 * @param recebe um codigo do usuário para pegar todos os comunicados
	 * @return retorna uma lista dos comunicados naquele usuário
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public List<Usuario> consultarUsuarioNome(String nome) throws Exception {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		stmt = con.prepareStatement("SELECT * FROM T_SPC_USUARIO WHERE NM_NOME LIKE ?");
		stmt.setString(1, "%"+nome+"%");
		rs = stmt.executeQuery();
		while(rs.next()) {
			listaUsuario.add(new Usuario(
					rs.getInt("CD_USUARIO"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_NOME"),
					rs.getLong("NR_CPF"),
					rs.getString("DS_EMAIL"),
					rs.getLong("NR_TELEFONE")));
		}
		
		
		return listaUsuario;
	}
	
	/**
	 * Método que pegar o maior codigo do usuario
	 * Consulta as linhas do usuario  e pega o maior número
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
