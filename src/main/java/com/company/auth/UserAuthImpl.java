package com.company.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.company.data.UserStruct;
import com.company.db.DatabaseConnector;

public class UserAuthImpl implements UserAuth {

	@Override
	public UserStruct login(String username, String password) {
		String UserLogin = "SELECT user_id, username, salt, created_at from user where username = ? and password = SHA2(CONCAT(?, (select salt from user where username = ?)), 512);";
		
		try {
			Connection connection = DatabaseConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(UserLogin);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, username);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
                int customerId = rs.getInt("user_id");
                String customerName = rs.getString("username");
                String salt = rs.getString("salt");
                String createdAt = rs.getString("created_at");
                UserStruct user = new UserStruct(customerId, customerName, salt, createdAt);
				return user;
            }
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}

	public String salt() {
        String SaltGenerator = "SELECT CEILING(RAND() * 1000000000000000) as salt";
		try {
			Connection connection = DatabaseConnector.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(SaltGenerator);
			
			// Retrieve and display the result
            if (resultSet.next()) {
                long salt = resultSet.getLong("salt");
                return String.valueOf(salt);
            }
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	@Override
	public String register(String username, String password) {
		String UserRegister = "INSERT INTO user (username, password, salt, created_at) VALUES (?, SHA2(CONCAT(?, ?), 512), ?, NOW());";
		String hash_salt = salt();
		
		try {
			Connection connection = DatabaseConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(UserRegister);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, hash_salt);
			stmt.setString(4, hash_salt);
			
			int num = stmt.executeUpdate();
            if(num > 0) {
            	return "Success";
            }
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}
