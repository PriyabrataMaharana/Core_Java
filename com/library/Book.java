package com.library;

public class Book {

	private int serialNo;
	private String title;
	private String author;
	private boolean isIssued;
	
	public Book(int serialNo, String title, String author) {
		super();
		this.serialNo = serialNo;
		this.title = title;
		this.author = author;
		this.isIssued = false;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isIssued() {
		return isIssued;
	}

	public void issue() {
		isIssued = true;
	}
	
	public void returnBook() {
		isIssued = false;
	}

	@Override
    public String toString() {
        return "| S.No: " + serialNo + " | " + title + " | by " + author + " - " + (isIssued ? "Issued" : "Available");
    }
	
}
