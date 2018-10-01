package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.spc.beans.Condominio;
import br.com.fiap.spc.beans.Endereco;
import br.com.fiap.spc.conexao.Conexao;
/**
 * 
 * Classe que manipula a tabela T_SPC_CONDOMINIO
 * Vers�o 1.0 - vers�o inicial com m�todos CRUD
 * 
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.beans.CondominioBO
 * @see br.com.fiap.spc.beans.Condominio
 *
 */
public class CondominioDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	/**
	 * M�todo construtor - abre conex�o com Banco
	 * 
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public CondominioDAO() throws Exception{
		con = Conexao.conectar();
	}
	
	/**
	 * M�todo de adicionar um novo condominio na T_SPC_CONDOMINIO e e inserir no bando de dados
	 * Todas a colunas obrigat�rias com o email e senha opcionais
	 * @param a recebe o objeto Acesso do beans
	 * @return Retorna uma String com de mensagem do do Condominio Cadastrado!
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 * 
	 */
	public String gravar(Condominio cond) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SPC_CONDOMINIO (CD_CONDOMINIO, CD_ENDERECO, NM_RAZAO_SOCIAL, NR_CNPJ, DS_TIPO_CONDOMINIO, NR_TELEFONE, DS_EMAIL)"
				+ "VALUES (?,?,?,?,?,?,?)");
		stmt.setInt(1, cond.getCodigoCondominio());
		stmt.setInt(2, cond.getEndereco().getCodigoEndereco());
		stmt.setString(3, cond.getRazaoSocial());
		stmt.setLong(4, cond.getCnpj());
		stmt.setInt(5, cond.getDs_tipo_condominio());
		stmt.setLong(6, cond.getTelefone());
		stmt.setString(7, cond.getEmail());
		stmt.executeUpdate();
		return "OK";
	}
	/**
	 * M�todo que pega o condominio do usuario registrado consultando a tabela T_SPC_CONDOMINIO
	 * Pega as colunas do condominio
	 * @param codigo � um int do codigo do condominio 
	 * @return Retorna um objeto Condominio que cont�m os daods do condominio
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public Condominio consultarCondominio(int numero) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SPC_CONDOMINIO WHERE CD_CONDOMINIO=?");
		stmt.setInt(1, numero);
		rs = stmt.executeQuery();
		Condominio cond = new Condominio();
		if(rs.next()) {
			cond.setCodigoCondominio(rs.getInt("CD_CONDOMINIO"));
			cond.setEndereco(new Endereco (rs.getInt("CD_ENDERECO")));
			cond.setRazaoSocial(rs.getString("NM_RAZAO_SOCIAL"));
			cond.setCnpj(rs.getLong("NR_CNPJ"));
			cond.setDs_tipo_condominio(rs.getInt("DS_TIPO_CONDOMINIO"));
			cond.setTelefone(rs.getLong("NR_TELEFONE"));
			cond.setEmail(rs.getString("DS_EMAIL"));
		}
		return cond;	
	}
	
	// CONSULTAR PELA RAZ�O SOCIAL
	/**
	 * M�todo que pega o objeto condominio atrav�z do nome
	 * Pega todos os dados do condom�nio
	 * @param recebe uma String razao que � o nome do condominio
	 * @return retorna um objeto condom�nio
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public Condominio consultarCondRazaoSocial(String razao) throws Exception {
		stmt = con.prepareStatement("SELECT NM_RAZAO_SOCIAL FROM T_SPC_CONDOMINIO WHERE NM_RAZAO_SOCIAL=?");
		stmt.setString(1, razao);
		rs = stmt.executeQuery();
		Condominio cond = new Condominio();
		if(rs.next()) {
			cond.setRazaoSocial(rs.getString("NM_RAZAO_SOCIAL"));			
		}
		return cond;	
	}
	
	/**
	 * M�todo que pegar o maior codigo do condominio
	 * Pega as colunas de codigo de condominio e pega o maior
	 * @param n�o recebe param�tros
	 * @return retorna um int com o codigo do maior acesso
	 * @throws Exception (valida uma exce��o checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public int maiorCodigo() throws Exception {
		stmt = con.prepareStatement("SELECT MAX(CD_CONDOMINIO) FROM T_SPC_CONDOMINIO");
		rs = stmt.executeQuery();
		rs.next();
		
		return rs.getInt("MAX(CD_CONDOMINIO)");
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
