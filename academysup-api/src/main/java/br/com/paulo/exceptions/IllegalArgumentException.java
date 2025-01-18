package br.com.paulo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgumentException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public IllegalArgumentException(String ex) {
		super(ex);
	}

}
