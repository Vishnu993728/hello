package com.vishnu.hello.customException;

public class StudentException extends RuntimeException {

	private String message;

	public StudentException(String message) {
		super(message);
	}
	
}
