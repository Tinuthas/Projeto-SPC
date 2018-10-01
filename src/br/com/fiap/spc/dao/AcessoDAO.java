package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.spc.beans.Acesso;
import br.com.fiap.spc.beans.Usuario;
import br.com.fiap.spc.conexao.Conexao;
/**
 * 
 * Classe que manipula a tabela T_SPC_ACESSO
 * Versão 1.0 - versão inicial com métodos CRUD
 * 
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.beans.AcessoBO
 * @see br.com.fiap.spc.beans.Acesso
 *
 */
public class AcessoDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 * Método construtor - abre conexão com Banco
	 * 
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public AcessoDAO() throws Exception {
		con = Conexao.conectar();
	}
	
	// REGISTRAR ACESSO (FUNCIONALIDADE 1)
	
	/**
	 * Método de adicionar um novo acesso na T_SPC_ACESSO e registrar seu acesso
	 * Todas a colunas obrigatórias
	 * @param a recebe o objeto Acesso do beans
	 * @return Retorna uma String com de mensagem do Acesso Liberado!
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 * 
	 */
	
	public String gravar(Acesso a) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SPC_ACESSO (CD_ACESSO, CD_USUARIO, DT_ACESSO) "
				+ "VALUES (?,?,SYSDATE)");
		stmt.setInt(1, a.getCodigoAcesso());
		stmt.setInt(2, a.getUsuario().getCodigoUsuario());
		stmt.executeUpdate();
		return "Acesso Liberado!";
	}
	
	/**
	 * Método que pega todos os acessos do usuario através de seu código usa as tabelas T_SPC_ACESSO e T_SPC_USUARIO
	 * Pega as colunas do numero de acesso e pega todos os dados da tabela usuário
	 * @param codigo é um int do numero codigo do usuario de acesso
	 * @return Retorna um objeto Acesso que contem o dados do usuário
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public Acesso consultarAcesso(int codigo) throws Exception {
		stmt = con.prepareStatement("SELECT AC.CD_ACESSO, U.CD_USUARIO, U.DS_SENHA, U.NM_NOME, U.NR_CPF, U.DS_EMAIL, U.NR_TELEFONE FROM T_SPC_ACESSO AC INNER JOIN T_SPC_USUARIO U ON (AC.CD_USUARIO = U.CD_USUARIO) WHERE U.CD_USUARIO=?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		Acesso a = new Acesso();
		if(rs.next()) {
			a.setCodigoAcesso(rs.getInt("CD_ACESSO"));
			a.setUsuario(new Usuario(rs.getInt("CD_USUARIO"),
								   rs.getString("DS_SENHA"),
								   rs.getString("NM_NOME"),
								   rs.getLong("NR_CPF"),
								   rs.getString("DS_EMAIL"),
								   rs.getLong("NR_TELEFONE")));
			
		}
		
		return a;
	}
	
	/**
	 * Método que pegar o maior codigo de acesso
	 * Pega as colunas de numero de acesso e pega o maior
	 * @param não recebe paramêtros
	 * @return retorna um int com o codigo do maior acesso
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	// PEGAR O MAIOR NUMERO PARA REGISTRAR O PRÓXIMO
	public int maiorCodigo() throws Exception {
		stmt = con.prepareStatement("SELECT MAX(CD_ACESSO) FROM T_SPC_ACESSO");
		rs = stmt.executeQuery();
		rs.next();
		return rs.getInt("MAX(CD_ACESSO)");
		

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
