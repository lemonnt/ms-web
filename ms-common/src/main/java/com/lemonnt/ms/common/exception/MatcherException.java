/**  
 * Project Name:ms-common  
 * File Name:MatcherException.java  
 * Package Name:com.lemonnt.ms.common.exception  
 * Date:Dec 14, 20169:43:05 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-common  
 * File Name: MatcherException.java  
 * Package Name: com.lemonnt.ms.common.exception  
 * Date: Dec 14, 20169:43:05 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.common.exception;

import com.lemonnt.ms.common.exception.core.ECode;
import com.lemonnt.ms.common.exception.core.RootException;

/**  
 * ClassName: MatcherException <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 14, 2016 9:43:05 AM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public class MatcherException extends RootException{
	
	/**  
	 * serialVersionUID:TODO 
	 * @since JDK 1.6  
	 */
	private static final long serialVersionUID = 1L;

	/**  
	 * Creates a new instance of ProductException.  
	 *  
	 * @param code
	 * @param args  
	 */  
	
	public MatcherException(Integer code, Object... args) {
		super(code,new ECode(null), args);  
		
	}

	public MatcherException(String code,Object... args) {
		super(code,new ECode(null),args);  
		
	}

}
  
