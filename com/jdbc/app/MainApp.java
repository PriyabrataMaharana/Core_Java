package com.jdbc.app;

import java.util.Scanner;

public class MainApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		while(running) {
			System.out.println("\n--- Student Management System ---\n");
			System.out.println("1. Insert Student");
			System.out.println("2. Update Student");
			System.out.println("3. Delete Student");
			System.out.println("4. View All Students");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: (1-5)");
			
			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline

			switch (choice) {
			case 1:
				DBOperations.insertStudent(scanner);
				break;
			case 2:
				DBOperations.updateStudent(scanner);
				break;
			case 3:
				DBOperations.deleteStudent(scanner);
				break;
			case 4:
				DBOperations.viewAllStudents();
				break;
			case 5:
				running = false;
				System.out.println("\n---- Exiting application. Goodbye! ----");
				break;
			default:
				System.out.println("Invalid choice.. Please try again.");
			}
		}
		scanner.close();
	}
}
