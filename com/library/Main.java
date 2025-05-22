package com.library;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Library lb = new Library();
		
		boolean running = true;
		
		while(running) {
			
			System.out.println("=========== Welcome to Library Management System ==========");
			System.out.println("1. Add Book");
			System.out.println("2. View Book");
			System.out.println("3. Issue Book");
			System.out.println("4. Return Book");
			System.out.println("5. Exit");
			System.out.println("Choose one operation - (1-5)");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				// Sl no, Title, Author
				System.out.println("Enter Serial No: ");
				int slNo = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Title: ");
				String title = sc.nextLine();
				System.out.println("Enter Author: ");
				String author = sc.nextLine();
				lb.addBook(new Book(slNo, title, author));
				System.out.println("Book added Successfully with Sl No: "+slNo+"\n");
				break;
			case 2:
				System.out.println("Here is the List of available books: ");
				lb.viewBooks();
				break;
			case 3:
				System.out.println("Enter serial No to issue: ");
				int issueSlNo = sc.nextInt();
				if(lb.issueBook(issueSlNo)) {
					System.out.println("Book Issued Successfully...");
				} else {
					System.out.println("Book Can not be issued...");
				}
				break;
			case 4:
				System.out.println("Enter serial No to return: ");
				int returnSlNo = sc.nextInt();
				if(lb.returnBook(returnSlNo)) {
					System.out.println("Book returned Successfully...");
				} else {
					System.out.println("Book Can not be returned...");
				}
				break;
			case 5:
				running = false;
				break;
			default:
				System.out.println("Invalid Option..");
			}
		}
		sc.close();
	}
}
