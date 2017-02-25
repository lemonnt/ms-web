package com.lemonnt.ms.common.exception;

public class RunFileException extends RootException{
	private static final long serialVersionUID = 1L;
	private String cause;
	public RunFileException(String msg) {
		super(msg);
		this.cause = msg;
	}	
	
	public RunFileException(String msg,Throwable e) {
		super(msg,e);
		this.cause = msg;
	}	
	public String getMsg(){
		return cause;
	}


}
