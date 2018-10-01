package br.com.fiap.spc.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

// CLASSE PARA RETORNAR CONEXÃO
public class Conexao {
	
	public static Connection conectar() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL","RM81549","150400");
	}

}
