package com.user.management.user.model;

public class ResetPassword {
	
	private String updatedPassword;
	private String message;

	public ResetPassword( String updatedPassword, String message) {
		super();
		this.updatedPassword = updatedPassword;
		this.message = message;
	}

	public ResetPassword() {

	}

	

	public String getUpdatedPassword() {
		return updatedPassword;
	}

	public void setUpdatedPassword(String updatedPassword) {
		this.updatedPassword = updatedPassword;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
