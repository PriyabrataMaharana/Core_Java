package com.jdbc.learn;

import java.sql.*;
import java.util.Scanner;

public class UpdateStatement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Load DB driver and get connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jdbc";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);

            // Take ID input
            System.out.print("Enter Student ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Check if student exists
            String checkQuery = "SELECT * FROM student WHERE id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Student exists, ask for new name and city
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new city: ");
                String newCity = scanner.nextLine();

                // Update record
                String updateQuery = "UPDATE student SET name = ?, city = ? WHERE id = ?";
                PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                updateStmt.setString(1, newName);
                updateStmt.setString(2, newCity);
                updateStmt.setInt(3, id);

                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student data updated successfully.");
                } else {
                    System.out.println("Update failed.");
                }

                updateStmt.close();
            } else {
                System.out.println("Student with this id doesn't exist.");
            }

            rs.close();
            checkStmt.close();
            con.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

