/**
 * 
 */
package com.lemonnt.ms.lsf.core;

/**
 * @author GavinLee
 *
 */
public class IllegalMethodException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	/**
	 * 
	 */
	public IllegalMethodException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMessage(){
		return msg;
	}
}
