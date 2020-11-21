package com.dinesh.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book", schema = "public")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", nullable = false)
	private long bookId;
	
	@Column(name = "book_name", nullable = true)
	private String bookName;
	
	@Column(name = "book_category", nullable = true)
	private String bookCategory;
	
	@Lob
	@Column(name = "book_image", nullable = true)
	private String bookImage;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name = "publisher_id", nullable = false)
	private Publisher publisher;
	
	@Column(name = "is_delete", nullable = false)
	private byte isDelete;

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

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	
	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(byte isDelete) {
		this.isDelete = isDelete;
	}
	
}
