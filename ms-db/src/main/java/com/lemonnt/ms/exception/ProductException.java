/**  
 * Project Name:ms-db  
 * File Name:ProductException.java  
 * Package Name:com.lemonnt.ms.exception  
 * Date:Dec 13, 20161:32:33 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-db  
 * File Name: ProductException.java  
 * Package Name: com.lemonnt.ms.exception  
 * Date: Dec 13, 20161:32:33 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.exception;

import com.lemonnt.ms.common.exception.core.ECode;
import com.lemonnt.ms.common.exception.core.RootException;

/**  
 * ClassName: ProductException <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 13, 2016 1:32:33 PM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public class ProductException extends RootException{

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
	
	public ProductException(Integer code, Object... args) {
		super(code,new ECode("productManagemet"), args);  
		
	}

	public ProductException(String code,Object... args) {
		super(code,new ECode("productManagemet"),args);  
		
	}
}
  
