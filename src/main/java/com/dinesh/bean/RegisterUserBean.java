package com.dinesh.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterUserBean {
	
	@NotNull(message = "User Name is missing")
	@Size(min = 1, message = "User Name is empty")
	private String userName;
	
	@NotNull(message = "Email is missing")
	@Size(min = 1, message = "Email is empty")
	@Email(message = "Email is not vaild")
	private String emailId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
