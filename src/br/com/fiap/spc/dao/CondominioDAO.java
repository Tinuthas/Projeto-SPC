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
 * Versão 1.0 - versão inicial com métodos CRUD
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
	 * Método construtor - abre conexão com Banco
	 * 
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public CondominioDAO() throws Exception{
		con = Conexao.conectar();
	}
	
	/**
	 * Método de adicionar um novo condominio na T_SPC_CONDOMINIO e e inserir no bando de dados
	 * Todas a colunas obrigatórias com o email e senha opcionais
	 * @param a recebe o objeto Acesso do beans
	 * @return Retorna uma String com de mensagem do do Condominio Cadastrado!
	 * @throws Exception (valida uma exceção checked SQLException)
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
	 * Método que pega o condominio do usuario registrado consultando a tabela T_SPC_CONDOMINIO
	 * Pega as colunas do condominio
	 * @param codigo é um int do codigo do condominio 
	 * @return Retorna um objeto Condominio que contêm os daods do condominio
	 * @throws Exception (valida uma exceção checked SQLException)
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
	
	// CONSULTAR PELA RAZÃO SOCIAL
	/**
	 * Método que pega o objeto condominio atravéz do nome
	 * Pega todos os dados do condomínio
	 * @param recebe uma String razao que é o nome do condominio
	 * @return retorna um objeto condomínio
	 * @throws Exception (valida uma exceção checked SQLException)
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
	 * Método que pegar o maior codigo do condominio
	 * Pega as colunas de codigo de condominio e pega o maior
	 * @param não recebe paramêtros
	 * @return retorna um int com o codigo do maior acesso
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public int maiorCodigo() throws Exception {
		stmt = con.prepareStatement("SELECT MAX(CD_CONDOMINIO) FROM T_SPC_CONDOMINIO");
		rs = stmt.executeQuery();
		rs.next();
		
		return rs.getInt("MAX(CD_CONDOMINIO)");
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
