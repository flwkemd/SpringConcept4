package com.ex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestJdbc {
	public static void main(String[] args) {
		
	String jdbcUrl = "jdbc:mysql://localhost:3307/hb-01-one-to-one-uni?userSSL=false";
	String user = "hbstudent";
	String pass = "hbstudent";
	try {
		System.out.println("Connecting to database: " +jdbcUrl);
		
		Connection myConn =
				DriverManager.getConnection(jdbcUrl, user, pass);
		
		System.out.println("Connection success");
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	}
}
