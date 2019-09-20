package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	private static Connection connection;

	private static boolean isTest = false;

	public static Connection getConnection() throws SQLException {
		
		if(isTest==true) {
			return 
			getH2Connection();
		}else {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			String url = System.getenv("URLAPP");
			String username = System.getenv("USERAPP");
			String password = System.getenv("PASSWORDAPP");

			if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(url, username, password);
			}

			return connection;

		}
		
	}
	
	public static Connection getH2Connection() {
		
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			
			if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:h2:~/test","","");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

}
