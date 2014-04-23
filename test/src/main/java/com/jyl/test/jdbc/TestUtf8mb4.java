package com.jyl.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUtf8mb4 {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		 
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		String jdbcUrl = "jdbc:mysql://www.sharera.net:6888/capital_dev?useUnicode=true&amp;characterEncoding=UTF-8&amp;sessionVariables=character_set_connection/utf8mb4";
		String user = "capital";
		String pass = "capital";
		
		Connection conn=DriverManager.getConnection(jdbcUrl,user,pass);
		String query = "show variables like 'character_set_%'";
		Statement stat = conn.createStatement();
		
		stat.execute(query);
		
		ResultSet rs = stat.getResultSet();
		while (rs.next()){
		    String k = rs.getString(1);
		    String v = rs.getString(2);
		    System.out.println(k + " - " + v);
		}
		
		System.out.println("----------------------------------------");
		stat.execute("SET character_set_client = utf8mb4, character_set_connection = utf8mb4, character_set_database = utf8mb4, " +
        		"character_set_results = utf8mb4, character_set_server = utf8mb4");
		
		stat.execute(query);
		
		ResultSet rs1 = stat.getResultSet();
		while (rs1.next()){
		    String k = rs1.getString(1);
		    String v = rs1.getString(2);
		    System.out.println(k + " - " + v);
		}
		
		stat.close();
		conn.close();
	}
}
