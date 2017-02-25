package com.lemonnt.ms.common.exception;

public class AuthenticationException extends Exception {

	private static final long serialVersionUID = 1L;
	private String cause;
	public AuthenticationException(String msg) {
		super(msg);
		this.cause = msg;
	}	
	
	public AuthenticationException(String msg,Throwable e) {
		super(msg,e);
		this.cause = msg;
	}	
	public String getMsg(){
		return cause;
	}
}
