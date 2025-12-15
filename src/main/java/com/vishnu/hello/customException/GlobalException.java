package com.vishnu.hello.customException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
   @ExceptionHandler(StudentException.class)
	public ResponseEntity<String> handleResourceNotFound(StudentException se)
	{
		return new ResponseEntity<String>(se.getMessage(), HttpStatus.NOT_FOUND);
	}
}
 