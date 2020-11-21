package com.dinesh.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditBookBean {
	
	@NotNull(message = "bookId is missing")
	@Min(value = 0, message = "bookId is empty")
	private long bookId;
	
	@NotNull(message = "Book Name is missing")
	@Size(min = 1, message = "Book Name is empty")
	private String bookName;
	
	private String bookImage;
	
	private String bookCategory;
	
	@NotNull(message = "Author Name is missing")
	@Size(min = 1, message = "Author Name is empty")
	private String authorName;
	
	@NotNull(message = "Publisher Name is missing")
	@Size(min = 1, message = "Publisher Name is empty")
	private String publisherName;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	
	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

}
