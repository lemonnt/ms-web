package com.lemonnt.ms.common.exception;

public class RootException extends RuntimeException{
	private String cause;
	private static final long serialVersionUID = 1L;
	
	public RootException(String msg) {
		super(msg);
		this.cause = msg;
	}

	public RootException(String msg,Throwable e) {
		super(msg,e);
		this.cause = msg;
	}

	public String getMsg(){
		return cause;
	}
	
	public StackTraceElement getMsg(Exception e){
		StackTraceElement stackTraceElement = e.getStackTrace()[0];		
		return stackTraceElement;
	}
	
	public StackTraceElement getReason(Exception e){
		StackTraceElement stackTraceElement =e.getStackTrace()[1];
		return stackTraceElement;
	}


}
