package br.com.fiap.spc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.spc.beans.Comunicado;
import br.com.fiap.spc.conexao.Conexao;


 /**
 * 
 * Classe que manipula a tabela T_SPC_COMUNICADO
 * Versão 1.0 - versão inicial com métodos CRUD
 * 
 * @author Marcus V. Galdino
 * @version 1.0
 * @since 1.0
 * @see br.com.fiap.spc.beans.ComunicadoBO
 * @see br.com.fiap.spc.beans.Comunicado
 *
 */


public class ComunicadoDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 * Método construtor - abre conexão com Banco
	 * 
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public ComunicadoDAO() throws Exception{
		con = Conexao.conectar();
	}
	
	// REGISTRAR COMUNICADOS (FUNCIONALIDADE 2)
	
	/**
	 * 
	 * Método de adicionar um novo comunicado na T_SPC_COMUNICADO e guardar os comunicados
	 * Colunas obrigatórios NR_ENVIO, CD_USUARIO, DS_COMUNICADO, DT_COMUNICADO e os outros opcional
	 * @param a recebe o objeto Comunicado do beans
	 * @return Retorna uma String com de mensagem do Comunicado enviado!
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public String gravar(Comunicado comu) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SPC_COMUNICADO (NR_ENVIO, CD_USUARIO, DS_ASSUNTO, DS_COMUNICADO, DT_COMUNICADO, DT_HORA_INICIO, DT_HORA_TERMINO, DS_LOCAL) "
				+ "VALUES (?,?,?,?,SYSDATE,TO_DATE(?,'dd/mm/yyyy hh24:mi:ss'),TO_DATE(?,'dd/mm/yyyy hh24:mi:ss'),?)");
		stmt.setInt(1, comu.getNumeroEnvio());
		stmt.setInt(2, comu.getPessoa().getCodigoUsuario());
		stmt.setString(3,  comu.getAssunto());
		stmt.setString(4, comu.getDescricao());
		stmt.setString(5, comu.getInicio());
		stmt.setString(6, comu.getTermino());
		stmt.setString(7, comu.getLocal());
		stmt.executeUpdate();
		
		
		return "Comunicado enviado";
	}
	
	/**
	 * Método que pegar o maior codigo do comunicado
	 * Pega as colunas de numero de comunicado e pega o maior
	 * @param não recebe paramêtros
	 * @return retorna um int com o codigo do maior comunicado
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	// PEGAR O MAIOR NUMERO PARA REGISTRAR O PRÓXIMO
	public int maiorCodigo() throws Exception {
		stmt = con.prepareStatement("SELECT MAX(NR_ENVIO) FROM T_SPC_COMUNICADO");
		rs = stmt.executeQuery();
		rs.next();
		return rs.getInt("MAX(NR_ENVIO)");
		
	}
	
	/**
	 * Método para transformar String em Date e formatar
	 * @param recebe uma String data para transformar em data
	 * @return uma Date
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	public Date stringToDate(String data) throws Exception{
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        f.setLenient(false);
        java.util.Date d1 = null;
        d1 = f.parse(data);
        
        return (Date) d1;
    }
	
	/**
	 * Método que consulta a lista de comunicados do código de usuário
	 * Pega um codigo que consultara os dados como assunto, data e descrição do comunicado
	 * @param recebe um codigo do usuário para pegar todos os comunicados
	 * @return retorna uma lista dos comunicados naquele usuário
	 * @throws Exception (valida uma exceção checked SQLException)
	 * @author Marcus V. Galdino
	 */
	
	
	public List<Comunicado> consultarComunicado(int codigo) throws Exception {
		List<Comunicado> listComu = new ArrayList<Comunicado>();
		Comunicado comu = new Comunicado();
		stmt = con.prepareStatement("SELECT COMU.DS_ASSUNTO, COMU.DT_COMUNICADO, COMU.DS_COMUNICADO "
				+ " FROM T_SPC_COMUNICADO COMU INNER JOIN T_SPC_PESSOA P ON (COMU.CD_USUARIO = P.CD_USUARIO) "
				+ "INNER JOIN T_SPC_CONDOMINIO COND ON (COND.CD_CONDOMINIO = P.CD_CONDOMINIO) "
				+ " WHERE COND.CD_CONDOMINIO = (SELECT CD_CONDOMINIO FROM T_SPC_PESSOA WHERE CD_USUARIO = ?)");
		
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		while(rs.next()) {
			comu.setAssunto(rs.getString("DS_ASSUNTO"));
			comu.setData(rs.getString("DT_COMUNICADO"));
			comu.setDescricao(rs.getString("DS_COMUNICADO"));
			listComu.add(comu);
			
		}
		
		return listComu;
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
