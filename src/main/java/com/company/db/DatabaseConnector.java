package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	private static final String URL = "jdbc:mysql://localhost:3306/todo_system";
	  private static final String USERNAME = "root";
	  private static final String PASSWORD = "3j27s4786";
	  
	  static {
	    try {
	      // Load the MySQL JDBC driver
	      Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }
	  }

	  public static Connection getConnection() throws SQLException {
		  System.out.println("DB Connected");
	      return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	  }
}
