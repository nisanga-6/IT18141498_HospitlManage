package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class user {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_user?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readUsers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User Name</th><th> Name</th><th>Password</th> <th>Update</th><th>Remove</th></tr>";
			String query = "select * from reguser";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String userid = Integer.toString(rs.getInt("userid"));
				String username = rs.getString("username");
				String name = rs.getString("name");

				String password = rs.getString("password");

				// Add into the html table
				output +=  "<tr><td><input id='hiduserIDUpdate' name='hiduserIDUpdate' type= 'hidden' value= '" + userid + "'>" + username + "</td>"; 
				
				output += "<td>" + name + "</td>";
				output += "<td>" + password + "</td>";

				// buttons
				
				output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + userid + "'>" + " </td></tr>";
						
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the users.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertUser(String username, String name, String password) 
	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into reguser(`userid`,`username`,`name`,`password`)" + "values(?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, username);
			preparedStmt.setString(3, name);

			preparedStmt.setString(4, password);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
		} catch (Exception e) 
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String userid, String username, String name, String password) 
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE `reguser`" 
			        + "SET `username`=?,name=?,password=?" 
					+ " WHERE `userid`=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, password);

			preparedStmt.setInt(4, Integer.parseInt(userid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUser(String userid)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for deleting.";
	 } 
	 System.out.println(userid);
	
	 // create a prepared statement
	 String query = "delete from reguser where userid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(userid));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newUsers = readUsers();
	 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
	 System.err.println(e);
	 }
	 return output;
	 }

}
