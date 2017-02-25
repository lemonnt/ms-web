/**  
 * Project Name:tahoe-common  
 * File Name:TestException.java  
 * Package Name:com.cisco.webex.frame.file.exception  
 * Date:Dec 7, 20161:37:33 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: tahoe-common  
 * File Name: TestException.java  
 * Package Name: com.cisco.webex.frame.file.exception  
 * Date: Dec 7, 20161:37:33 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.common.exception.core;


/**  
 * ClassName: RootException <br/>
 * Function:  should be extended by the extensive exception<br/>
 * Reason:  <br/>
 * date: Dec 7, 2016 1:37:33 PM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public class RootException extends AbstractRootException{
/**  
	 * serialVersionUID:TODO 
	 * @since JDK 1.6  
	 */
	private static final long serialVersionUID = 1L;

	/**  
	 * Creates a new instance of TestException.  
	 *  
	 * @param code  exception code from a locale file
	 */  
	public RootException(String code,IECode ieCode) {		
		super(code, ieCode.getCodeMessage(code, new Object[]{}));
	}

	/**  
	 * Creates a new instance of TestException.  
	 *  
	 * @param code exception code from a locale file
	 * @param args the parameters in the exception message
	 */  
	public RootException(String code,IECode ieCode, Object... args) {
		super(code, ieCode.getCodeMessage(code, args));

	}
	
	
	public RootException(Integer code,IECode ieCode, Object... args) {
		super(code, ieCode.getCodeMessage(code, args));

	}

	/**  
	 * Creates a new instance of TestException.  
	 *  
	 * @param t throwable
	 * @param code exception code from a locale file  
	 */  
	public RootException(Throwable t,IECode ieCode, String code) {
		super(t, code,ieCode.getCodeMessage(code,new Object[]{}));
	}


	/**  
	 * Creates a new instance of TestException.  
	 *  
	 * @param t
	 * @param code exception code from a locale file
	 * @param args the parameters in the exception message  
	 */  
	public RootException(Throwable t,IECode ieCode,String code, Object... args) {
		super(t, code, ieCode.getCodeMessage(code, args));
	}
}
  
