package com.jdbc.app;

import java.sql.*;
import java.util.Scanner;

public class DBOperations {

    public static void insertStudent(Scanner scanner) {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student city: ");
            String city = scanner.nextLine();

            String insert = "INSERT INTO student(name, city) VALUES(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insert);
            pstmt.setString(1, name);
            pstmt.setString(2, city);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Data inserted successfully." : "Failed to insert data.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(Scanner scanner) {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Student ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline

            String checkQuery = "SELECT * FROM student WHERE id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();

                System.out.print("Enter new city: ");
                String newCity = scanner.nextLine();

                String updateQuery = "UPDATE student SET name = ?, city = ? WHERE id = ?";
                PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                updateStmt.setString(1, newName);
                updateStmt.setString(2, newCity);
                updateStmt.setInt(3, id);

                int rowsAffected = updateStmt.executeUpdate();
                System.out.println(rowsAffected > 0 ? "Student data updated successfully." : "Update failed.");
            } else {
                System.out.println("Student with this id doesn't exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(Scanner scanner) {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Student ID to delete: ");
            int id = scanner.nextInt();

            String checkQuery = "SELECT * FROM student WHERE id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String deleteQuery = "DELETE FROM student WHERE id = ?";
                PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
                deleteStmt.setInt(1, id);

                int rowsDeleted = deleteStmt.executeUpdate();
                System.out.println(rowsDeleted > 0 ? "Student record deleted successfully." : "Deletion failed.");
            } else {
                System.out.println("Student with this id doesn't exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewAllStudents() {
        try (Connection con = DBConnection.getConnection()) {
            String selectAll = "SELECT * FROM student";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectAll);

            System.out.println("\n--- All Students ---\n");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                System.out.println("ID: " + id + ", Name: " + name + ", City: " + city);
            }

            if (!hasData) {
                System.out.println("No student records found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

