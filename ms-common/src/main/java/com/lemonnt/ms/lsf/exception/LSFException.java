package com.lemonnt.ms.lsf.exception;

public class LSFException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	

	public LSFException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public synchronized Throwable getCause() {
		return super.getCause();
	}
	
	

}
