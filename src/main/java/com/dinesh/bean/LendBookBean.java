package com.dinesh.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class LendBookBean {
	
	@NotNull(message = "bookId is missing")
	@Min(value = 0, message = "bookId is empty")
	private long bookId;
	
	@NotNull(message = "userId is missing")
	@Min(value = 0, message = "userId is empty")
	private long userId;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
