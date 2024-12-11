package com.company.todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import com.company.data.TodoStruct;
import com.company.db.DatabaseConnector;

public class TodoImpl implements Todo {

	@Override
	public boolean add(int user_id, String task, String due_date) {
		String insertQuery = "INSERT INTO todo (user_id, task, status, created_date, due_date) VALUES (?, ?, ?, CURDATE(), ?)";
		
		try {
            // Establish the connection
			Connection connection = DatabaseConnector.getConnection();

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, task); // Set task
            preparedStatement.setString(3, "pending"); // Set status
            preparedStatement.setString(4, due_date);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return false;
	}

	@Override
	public TodoStruct[] getpending(int user_id) {
		TodoStruct[] todoArray = new TodoStruct[0];
		String selectQuery = "SELECT * FROM todo WHERE user_id = ? and status = \"pending\"";
		
		try {
            // Establish the connection
			Connection connection = DatabaseConnector.getConnection();

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, user_id);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	TodoStruct todo = new TodoStruct(
            			resultSet.getInt("task_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("task"),
                        resultSet.getString("status"),
                        resultSet.getString("created_date"),
                        resultSet.getString("due_date"),
                        resultSet.getString("completion_date")
            			);
            	todoArray = Arrays.copyOf(todoArray, todoArray.length + 1);
                todoArray[todoArray.length - 1] = todo;
            }
            
            System.out.println("TODOIMPL GETPENDING "+ todoArray);
            return todoArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return null;
	}

	@Override
	public boolean delete(int task_id) {
		String deleteQuery = "DELETE FROM todo WHERE task_id = ?";
		
		try {
            // Establish the connection
			Connection connection = DatabaseConnector.getConnection();

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, task_id);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return false;
	}

	@Override
	public TodoStruct[] getcompleted(int user_id) {
		TodoStruct[] todoArray = new TodoStruct[0];
		String selectQuery = "SELECT * FROM todo where user_id = ? and status = \"completed\"";
		
		try {
            // Establish the connection
			Connection connection = DatabaseConnector.getConnection();

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, user_id);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	TodoStruct todo = new TodoStruct(
            			resultSet.getInt("task_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("task"),
                        resultSet.getString("status"),
                        resultSet.getString("created_date"),
                        resultSet.getString("due_date"),
                        resultSet.getString("completion_date")
            			);
            	todoArray = Arrays.copyOf(todoArray, todoArray.length + 1);
                todoArray[todoArray.length - 1] = todo;
            }
            
            System.out.println("TODOIMPL GETCOMPLETED "+ todoArray);
            return todoArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return null;
	}

	@Override
	public boolean complete(int task_id) {
		String updateQuery = "UPDATE todo SET status = \"completed\", completion_date = CURDATE() WHERE task_id = ?";
		
		try {
            // Establish the connection
			Connection connection = DatabaseConnector.getConnection();

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, task_id);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return false;
	}

	@Override
	public boolean update(int task_id, String task) {
		String updateQuery = "UPDATE todo SET task = ? WHERE task_id = ?";
		
		try {
            // Establish the connection
			Connection connection = DatabaseConnector.getConnection();

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, task);
            preparedStatement.setInt(2, task_id);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return false;
	}

}
