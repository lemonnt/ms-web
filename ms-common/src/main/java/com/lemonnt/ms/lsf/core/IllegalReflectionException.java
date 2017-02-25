/**
 * 
 */
package com.lemonnt.ms.lsf.core;

/**
 * @author GavinLee
 *
 */
public class IllegalReflectionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	/**
	 * 
	 */
	public IllegalReflectionException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public IllegalReflectionException(String msg,Throwable e) {
		super(msg,e);
		this.msg = msg;
	}
	
	

	public String getMessage(){
		return msg;
	}
}
