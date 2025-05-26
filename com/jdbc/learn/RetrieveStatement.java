package com.jdbc.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RetrieveStatement {

	public static void main(String[] args) {
		try {
			// DB Driver load and Connection got established..
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jdbc";
			String username = "root";
			String password = "root";
			Connection con = DriverManager.getConnection(url, username, password);
			
			// Retrieve data
			System.out.println("\n--- Retrieving All Data ---\n");
            String selectAll = "SELECT * FROM student";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectAll);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                System.out.println("ID: " + id + ", Name: " + name + ", City: " + city);
            }
            // Close ResultSet and Statement after use (important!)
            rs.close();
            stmt.close();
            con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
