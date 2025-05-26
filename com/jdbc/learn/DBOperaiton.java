package com.jdbc.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBOperaiton {

	public static void main(String[] args) {
		try {
			// DB Driver load and Connection got established..
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jdbc";
			String username = "root";
			String password = "root";
			Connection con = DriverManager.getConnection(url, username, password);
			
			// Creating a Table
			String createTable = "create table student(id int(20) primary key auto_increment, name varchar(200) not null, city varchar(200))";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(createTable);
            System.out.println("Student table got created..");
            
            con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
