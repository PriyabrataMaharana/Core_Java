package com.jdbc.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentManagementGUI extends JFrame {

    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(600, 400);
        setLocationRelativeTo(null); // center
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Insert", createInsertPanel());
        tabbedPane.add("Update", createUpdatePanel());
        tabbedPane.add("Delete", createDeletePanel());
        tabbedPane.add("View All", createViewPanel());

        add(tabbedPane);
        setVisible(true);
    }

    private JPanel createInsertPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField();

        JButton insertBtn = new JButton("Insert");
        JLabel msgLabel = new JLabel("");
        msgLabel.setForeground(Color.BLUE);

        insertBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String city = cityField.getText().trim();

            if (!name.isEmpty() && !city.isEmpty()) {
                boolean success = DBOperation.insertStudent(name, city);
                msgLabel.setText(success ? "Student inserted successfully!" : "Insert failed.");
                if (success) {
                    nameField.setText("");
                    cityField.setText("");
                }
            } else {
                msgLabel.setText("Please enter both name and city.");
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(new JLabel());
        panel.add(insertBtn);
        panel.add(new JLabel());
        panel.add(msgLabel);

        return panel;
    }

    private JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("New Name:");
        JTextField nameField = new JTextField();
        JLabel cityLabel = new JLabel("New City:");
        JTextField cityField = new JTextField();

        JButton updateBtn = new JButton("Update");
        JLabel msgLabel = new JLabel("");
        msgLabel.setForeground(Color.BLUE);

        updateBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String newName = nameField.getText().trim();
                String newCity = cityField.getText().trim();

                if (!newName.isEmpty() && !newCity.isEmpty()) {
                    boolean updated = DBOperation.updateStudent(id, newName, newCity);
                    msgLabel.setText(updated ? "Student updated successfully!" : "Student ID not found.");
                    if (updated) {
                        idField.setText("");
                        nameField.setText("");
                        cityField.setText("");
                    }
                } else {
                    msgLabel.setText("Name and City cannot be empty.");
                }
            } catch (NumberFormatException ex) {
                msgLabel.setText("Invalid ID.");
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(new JLabel());
        panel.add(updateBtn);
        panel.add(new JLabel());
        panel.add(msgLabel);

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();

        JButton deleteBtn = new JButton("Delete");
        JLabel msgLabel = new JLabel("");
        msgLabel.setForeground(Color.RED);

        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                boolean deleted = DBOperation.deleteStudent(id);
                msgLabel.setText(deleted ? "Student deleted successfully!" : "Student ID not found.");
                if (deleted) {
                    idField.setText("");
                }
            } catch (NumberFormatException ex) {
                msgLabel.setText("Invalid ID.");
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(new JLabel());
        panel.add(deleteBtn);
        panel.add(new JLabel());
        panel.add(msgLabel);

        return panel;
    }

    private JPanel createViewPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Name", "City"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        refreshTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshTable(model));
        panel.add(refreshBtn, BorderLayout.SOUTH);

        return panel;
    }

    private void refreshTable(DefaultTableModel model) {
        model.setRowCount(0);
        List<String[]> students = DBOperation.getAllStudents();
        for (String[] student : students) {
            model.addRow(student);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementGUI::new);
    }
}
