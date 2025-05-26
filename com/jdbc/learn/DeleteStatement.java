package com.jdbc.learn;

import java.sql.*;
import java.util.Scanner;

public class DeleteStatement {
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
            System.out.print("Enter Student ID to delete: ");
            int id = scanner.nextInt();

            // Check if student exists
            String checkQuery = "SELECT * FROM student WHERE id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Student exists, proceed to delete
                String deleteQuery = "DELETE FROM student WHERE id = ?";
                PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
                deleteStmt.setInt(1, id);

                int rowsDeleted = deleteStmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Student record deleted successfully.");
                } else {
                    System.out.println("Deletion failed.");
                }

                deleteStmt.close();
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

