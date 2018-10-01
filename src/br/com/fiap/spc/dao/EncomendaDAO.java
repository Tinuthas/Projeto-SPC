package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Encomenda;
import br.com.fiap.spc.conexao.Conexao;
/**
 * 
 * Classe que manipula a tabela T_SPC_ENCOMENDA
 * Versão 1.0 - versão inicial com métodos CRUD
 * 
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.beans.EncomendaBO
 * @see br.com.fiap.spc.beans.Encomenda
 *
 */
public class EncomendaDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	/**
	 * Método construtor - abre conexão com Banco
	 * 
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public EncomendaDAO() throws Exception {
		con = Conexao.conectar();
	}
	
	/**
	 * Método de adicionar uma nova encomenda na T_SPC_ENCOMENDA e registrar para o morador
	 * Todas a colunas obrigatórias
	 * @param a recebe o objeto Encomenda do beans
	 * @return Retorna uma String com de mensagem do Encomenda Gravada!
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 * 
	 */
	
	public String gravar(Encomenda enco) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SPC_ENCOMENDA (CD_MORADOR, CD_ENCOMENDA, CD_FUNCIONARIO, DS_TIPO_ENCOMENDA, DS_TAMANHO, DT_ENTREGA) "
				+ "VALUES (?,?,?,?,?,SYSDATE)");
		stmt.setInt(1, enco.getMorador().getCodigoUsuario());
		stmt.setInt(2, enco.getCodigoEncomenda());
		stmt.setInt(3, enco.getFuncionario().getCodigoUsuario());
		stmt.setInt(4, enco.getTipoEncomenda());
		stmt.setString(5, enco.getTamanho());
		stmt.executeUpdate();
		return "Encomenda gravada";
	}
	/**
	 * Método que consulta a encomenda para ver se existe o codigo na tabela T_SPC_ENCOMENDA
	 * Pega somente o codigo de encomenda
	 * @param codigo é um int do numero codigo do usuario de acesso
	 * @return Retorna um objeto Encomenda com o codigo inserirdo
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public Encomenda consultar(int codigo) throws Exception {
		stmt = con.prepareStatement("SELECT CD_ENCOMENDA FROM T_SPC_ENCOMENDA WHERE CD_ENCOMENDA=?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		
		Encomenda enco = new Encomenda();
		if(rs.next()) {
			enco.setCodigoEncomenda(rs.getInt("CD_ENCOMENDA"));
		}
		return enco;
	}
	
	/**
	 * Método que pegar o maior codigo da encomenda
	 * Consulta as linhas da encomenda e pega o maior codigo
	 * @param não recebe paramêtros
	 * @return retorna um int com o codigo do maior acesso
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public int maiorCodigo() throws Exception {
		stmt = con.prepareStatement("SELECT MAX(CD_ENCOMENDA) FROM T_SPC_ENCOMENDA");
		rs = stmt.executeQuery();
		rs.next();
		
		return rs.getInt("MAX(CD_ENCOMENDA)");
	}
	
	/**
	 * Método que consulta a lista de encomendas do código do morador
	 * Pega um codigo que consultara os dados da encomenda
	 * @param recebe um codigo do funcionario para pegar todas as encomendas
	 * @return retorna uma lista das encomendas naquele morador
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public List<Encomenda> consultaEncoMorador(int codigo) throws Exception {
		List<Encomenda> listaEncomenda = new ArrayList<Encomenda>();
		Encomenda enco = new Encomenda();
		stmt = con.prepareStatement("SELECT E.CD_ENCOMENDA, E.DS_TIPO_ENCOMENDA, E.DS_TAMANHO, E.DT_ENTREGA  FROM T_SPC_ENCOMENDA E INNER JOIN T_SPC_MORADOR M "
				+ " ON (E.CD_MORADOR = M.CD_MORADOR) WHERE M.CD_MORADOR=? ORDER BY E.DT_ENTREGA DESC");
		stmt.setInt(1, 1);
		rs = stmt.executeQuery();
		while(rs.next()) {
			enco.setCodigoEncomenda(rs.getInt("CD_ENCOMENDA"));
			enco.setTipoEncomenda(rs.getInt("DS_TIPO_ENCOMENDA"));
			enco.setTamanho(rs.getString("DS_TAMANHO"));
			enco.setEntrega(rs.getString("DT_ENTREGA"));
			listaEncomenda.add(enco);
		}
		
		return listaEncomenda;
	}
	
	/**
	 * Método que consulta a lista de encomendas do código do funcionario
	 * Pega um codigo que consultara os dadosda encomenda
	 * @param recebe um codigo do funcionario para pegar todas as encomendas
	 * @return retorna uma lista das encomendas naquele funcionario
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public List<Encomenda> consultaEncoFunc(int codigo) throws Exception {
		List<Encomenda> listaEncomenda = new ArrayList<Encomenda>();
		Encomenda enco = new Encomenda();
		stmt = con.prepareStatement("SELECT E.CD_ENCOMENDA, E.DS_TIPO_ENCOMENDA, E.DS_TAMANHO, E.DT_ENTREGA  FROM T_SPC_ENCOMENDA E INNER JOIN T_SPC_FUNCIONARIO F "
				+ " ON (E.CD_FUNCIONARIO = F.CD_FUNCIONARIO) WHERE F.CD_FUNCIONARIO=? ORDER BY E.DT_ENTREGA DESC");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		while(rs.next()) {
			enco.setCodigoEncomenda(rs.getInt("CD_ENCOMENDA"));
			enco.setTipoEncomenda(rs.getInt("DS_TIPO_ENCOMENDA"));
			enco.setTamanho(rs.getString("DS_TAMANHO"));
			enco.setEntrega(rs.getString("DT_ENTREGA"));
			listaEncomenda.add(enco);
		}
		
		return listaEncomenda;
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
