package com.lemonnt.ms.common.exception;

public class InvalidClazzType extends Exception {

	private static final long serialVersionUID = 1L;
	private String cause;
	public InvalidClazzType(String msg) {
		super(msg);
		this.cause = msg;
	}	
	
	public InvalidClazzType(String msg,Throwable e) {
		super(msg,e);
		this.cause = msg;
	}	
	
	
	@Override
    public String getMessage() {
	    return cause;
        
    }

    public String getMsg(){
		return cause;
	}
}
