package com.deutsche.trade.tradestoreservice.exception;

public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
