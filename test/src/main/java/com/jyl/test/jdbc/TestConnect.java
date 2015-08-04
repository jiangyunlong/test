package com.jyl.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnect {
	
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
		String url="jdbc:oracle:thin:@//192.168.8.220:1522/nis"; //orcl为数据库的SID 
		String user="nis"; 
		String password="xjh_scc"; 
		Connection conn= DriverManager.getConnection(url,user,password);
		System.out.println(conn);
	}
}
