package com.omeryaylaalti.usermanagement.dao.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2859292084648724403L;

	//throws Exception 
	
	public UserNotFoundException(String userId) {
		super("User not found with id: " + userId);
	}

}
