package com.jdbc.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertStatement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Load DB driver and get connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jdbc";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);

            // Take input from user
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student city: ");
            String city = scanner.nextLine();

            // Insert data into database
            String insert = "INSERT INTO student(name, city) VALUES(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insert);
            pstmt.setString(1, name);
            pstmt.setString(2, city);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }

            pstmt.close();
            con.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
