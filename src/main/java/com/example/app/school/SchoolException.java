package com.example.app.school;

@SuppressWarnings("serial")
public class SchoolException extends RuntimeException {

	  public SchoolException() { }
	    
	  public SchoolException(String message) {
	        super(message);
	  }

	  public SchoolException(String message, Throwable cause) {
	        super(message, cause);
	  }
	  
	  public SchoolException(Throwable cause) {
	        super(cause);
	  }
}
