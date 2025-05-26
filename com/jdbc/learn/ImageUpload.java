package com.jdbc.learn;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ImageUpload {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jdbc";
			String username = "root";
			String password = "root";
			Connection con = DriverManager.getConnection(url, username, password);
			String q = "insert into images(pic) values(?)";
			PreparedStatement pstmt = con.prepareStatement(q);
//			System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
			FileInputStream fis = new FileInputStream("download.png");
			pstmt.setBinaryStream(1, fis, fis.available());
			pstmt.executeUpdate();
			System.out.println("Done...");
		} catch(Exception e) {
			System.out.println("Error...");
		}
	}
}
