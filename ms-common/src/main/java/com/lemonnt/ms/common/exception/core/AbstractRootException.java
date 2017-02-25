/**  
 * Project Name: tahoe-common 
 * File Name: AbstractRootException.java  
 * Package Name: com.cisco.webex.frame.exception  
 * Date:Dec 7, 201612:10:09 AM  
 * Copyright (c) 2016, All Rights Reserved.  
 *  
 */

package com.lemonnt.ms.common.exception.core;

/**
 * 
 * ClassName: AbstractRootException <br/>
 * Function: The root exception should be extended by other extensive exception,
 * if developer wanna used the exception frame <br/>
 * Reason: Improvement the exception logic <br/>
 * date: Dec 7, 2016 10:29:18 AM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6
 */
public class AbstractRootException extends Exception {

	private static final long serialVersionUID = -4270866382608468516L;
	
	/**  
	 * code: exception code was configured in a properties file.
	 * @since JDK 1.6  
	 */
	private final String code;

	/**
	 * 
	 * Creates a new instance of AbstractRootException.  
	 *  
	 * @param code 
	 * @param message exception message
	 */
	public AbstractRootException(String code, String message) {
		super(message);
		this.code = code;
	}

	/**  
	 * Creates a new instance of AbstractRootException.  
	 *  
	 * @param code
	 * @param message  
	 */  
	public AbstractRootException(Integer code, String message) {
		super(message);
		this.code = String.valueOf(code);
	}

	
	/**  
	 * Creates a new instance of AbstractRootException.  
	 *  
	 * @param t - Throwable
	 * @param code - exception code ,like 10001
	 * @param message - exception message,like Can't ping the host.
	 */  
	public AbstractRootException(Throwable t, String code, String message) {
		super(message, t);
		this.code = code;
	}

	/**Getting the exception code
	 * @Description: TODO
	 * @author gavli  
	 * @return  
	 * @since JDK 1.6  
	 */
	public String getCode() {
		return this.code;
	}

	public String toString() {
		StringBuilder content = new StringBuilder();
		content.append("EXCEPTION CODE : [").append(this.code).append("]\n").append(super.toString());
		return String.valueOf(content);
	}
	
}
