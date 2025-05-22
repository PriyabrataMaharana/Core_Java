package com.library;

import java.util.ArrayList;
import java.util.List;

public class Library {
	private List<Book> books = new ArrayList<Book>();
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void viewBooks() {
		for(Book book : books) {
			System.out.println(book);
		}
	}
	
	public Book findBySlNo(int serialNo) {
		for(Book book : books) {
			if(book.getSerialNo() == serialNo) {
				return book;
			}
		}
		return null;
	}
	
	public boolean issueBook(int serialNo) {
		Book book = findBySlNo(serialNo);
		if(book != null & !book.isIssued()) {
			book.issue();
			return true;
		}
		return false;
	}
	
	public boolean returnBook(int serialNo) {
		Book book = findBySlNo(serialNo);
		if(book != null & book.isIssued()) {
			book.returnBook();;
			return true;
		}
		return false;
	}
}
