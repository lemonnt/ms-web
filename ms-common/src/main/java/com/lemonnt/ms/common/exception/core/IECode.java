/**  
 * Project Name:ms-common  
 * File Name:IECode.java  
 * Package Name:com.lemonnt.ms.common.exception.core  
 * Date:Dec 13, 20166:25:07 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-common  
 * File Name: IECode.java  
 * Package Name: com.lemonnt.ms.common.exception.core  
 * Date: Dec 13, 20166:25:07 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.common.exception.core;  

/**  
 * ClassName: IECode <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 13, 2016 6:25:07 PM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public interface IECode {
	public String getCodeMessage(String code, Object... args);
	
	public String getCodeMessage(int code, Object... args);

}
  
