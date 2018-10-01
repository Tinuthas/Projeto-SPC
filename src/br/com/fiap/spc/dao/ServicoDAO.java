package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spc.beans.Servico;
import br.com.fiap.spc.conexao.Conexao;
/**
* 
* Classe que manipula a tabela T_SPC_SERVICO
* Versão 1.0 - versão inicial com métodos CRUD
* 
* @author Marcus V. Galdino
* @version 1.0
* @since 1.0
* @see br.com.fiap.spc.beans.ServicoBO
* @see br.com.fiap.spc.beans.Servico
*
*/
public class ServicoDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	/**
	 * Método construtor - abre conexão com Banco
	 * 
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public ServicoDAO() throws Exception {
		con = Conexao.conectar();
	}
	
	// REGISTRAR AGENDAMENTO (FUNCIONALIDADE 3)
	/**
	 * 
	 * Método de adicionar um novo serviço na T_SPC_SERVICO
	 * Colunas obrigatórios todas
	 * @param a recebe o objeto Servico do beans
	 * @return Retorna uma String com de mensagem do Serviço gravado!
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	public String gravar(Servico serv) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SPC_SERVICO (CD_SERVICO, CD_MORADOR, DS_TIPO_SERVICO, DT_INICIO, DT_TERMINO) "
				+ "VALUES (?,?,?,TO_DATE(?,'dd/mm/yyyy hh24:mi'),TO_DATE(?,'dd/mm/yyyy hh24:mi'))");
		stmt.setInt(1, serv.getCodigoServico());
		stmt.setInt(2, serv.getMorador().getCodigoUsuario());
		stmt.setInt(3, serv.getTipoServico());
		stmt.setString(4, serv.getInicio());
		stmt.setString(5, serv.getTermino());
		stmt.executeUpdate();
		System.out.println("VEJA");
		return "Serviço gravado";
	}
	/**
	 * Método que pega o codigo do serviço e consulta se existe
	 * Pega a coluna codigo do serviço
	 * @param codigo é um int do numero codigo do serviço
	 * @return Retorna um objeto Serviço que contem os dados do serviço
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 * 
	 */
	public Servico consultar(int codigo) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SPC_SERVICO WHERE CD_SERVICO=?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		Servico serv = new Servico();
		if(rs.next()) {
			serv.setCodigoServico(rs.getInt("CD_SERVICO"));
		}
			
		
		return serv;
	}
	
	/**
	 * Método que consulta a lista de servicos do código do condominio
	 * Pega um codigo que consultara os dados como tipo, data de inicio e de término do serviço
	 * @param recebe um codigo do condominio para pegar todos os serviços
	 * @return retorna uma lista dos serviços naquele condomínio
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	
	public List<Servico> consultaServico(int codigo) throws Exception {
		List<Servico> listaServico = new ArrayList<Servico>();
		stmt = con.prepareStatement("SELECT S.DS_TIPO_SERVICO, S.DT_INICIO, S.DT_TERMINO FROM T_SPC_SERVICO S INNER JOIN T_SPC_MORADOR M \r\n" + 
				" ON (S.CD_MORADOR = M.CD_MORADOR) INNER JOIN T_SPC_PESSOA P ON (P.CD_USUARIO = M.CD_MORADOR) INNER JOIN T_SPC_CONDOMINIO C ON (C.CD_CONDOMINIO = P.CD_CONDOMINIO) WHERE C.CD_CONDOMINIO = (SELECT CD_CONDOMINIO FROM T_SPC_PESSOA WHERE CD_USUARIO=?) ORDER BY S.DT_INICIO DESC");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		Servico serv = new Servico();
		while(rs.next()) {
			serv.setTipoServico(rs.getInt("DS_TIPO_SERVICO"));
			serv.setInicio(rs.getString("DT_INICIO"));
			serv.setTermino("DT_TERMINO");
			listaServico.add(serv);
		}
		
		return listaServico;
	}
	
	
	// PEGAR O MAIOR NUMERO PARA REGISTRAR O PRÓXIMO
	/**
	 * Método que pegar o maior codigo do serviço
	 * Consulta as linhas do serviço e pega o maior número
	 * @param não recebe paramêtros
	 * @return retorna um int com o codigo do maior serviço
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
		public int maiorCodigo() throws Exception {
			stmt = con.prepareStatement("SELECT MAX(CD_SERVICO) FROM T_SPC_SERVICO");
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt("MAX(CD_SERVICO)");
			
		}
		
//		/**
//		 * Método para transformar String em Date e formatar
//		 * @param recebe uma String data para transformar em data
//		 * @return uma Date
//		 * @throws Exception (valida uma exceção checked SQLException)
//		 * @author Marcus V. Galdino
//		 */
		
//		public Date stringToDate(String data) throws Exception{
//	        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//	        f.setLenient(false);
//	        java.util.Date d1 = null;
//	        d1 = f.parse(data);
//	        
//	        return (Date) d1;
//	    }
				
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
