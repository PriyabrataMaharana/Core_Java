package com.jdbc.gui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperation {

    // Wrap insert method - returns boolean success
    public static boolean insertStudent(String name, String city) {
        try (Connection con = DBConnection.getConnection()) {
            String insert = "INSERT INTO student(name, city) VALUES(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insert);
            pstmt.setString(1, name);
            pstmt.setString(2, city);

            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Wrap update method - returns boolean success
    public static boolean updateStudent(int id, String newName, String newCity) {
        try (Connection con = DBConnection.getConnection()) {
            String checkQuery = "SELECT * FROM student WHERE id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String updateQuery = "UPDATE student SET name = ?, city = ? WHERE id = ?";
                PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                updateStmt.setString(1, newName);
                updateStmt.setString(2, newCity);
                updateStmt.setInt(3, id);

                return updateStmt.executeUpdate() > 0;
            } else {
                return false; // id not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Wrap delete method - returns boolean success
    public static boolean deleteStudent(int id) {
        try (Connection con = DBConnection.getConnection()) {
            String checkQuery = "SELECT * FROM student WHERE id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String deleteQuery = "DELETE FROM student WHERE id = ?";
                PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
                deleteStmt.setInt(1, id);
                return deleteStmt.executeUpdate() > 0;
            } else {
                return false; // id not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to return all students as List of String arrays for table
    public static List<String[]> getAllStudents() {
        List<String[]> students = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String selectAll = "SELECT * FROM student";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectAll);

            while (rs.next()) {
                String[] student = new String[3];
                student[0] = String.valueOf(rs.getInt("id"));
                student[1] = rs.getString("name");
                student[2] = rs.getString("city");
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}

