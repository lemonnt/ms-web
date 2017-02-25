package com.lemonnt.ms.common.exception;

public class MessageException extends RootException {

	private static final long serialVersionUID = 1L;
	private String cause;
	public MessageException(String msg) {
		super(msg);
		this.cause = msg;
	}	
	
	public MessageException(String msg,Throwable e) {
		super(msg,e);
		this.cause = msg;
	}	
	public String getMsg(){
		return cause;
	}
}
