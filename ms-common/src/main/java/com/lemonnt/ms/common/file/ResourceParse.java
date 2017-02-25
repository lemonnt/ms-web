/**  
 * Project Name:ms-common  
 * File Name:FileLoader.java  
 * Package Name:com.lemonnt.ms.common.file  
 * Date:Dec 6, 201612:33:48 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
  
package com.lemonnt.ms.common.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**  
 * ClassName:FileLoader <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     Dec 6, 2016 12:33:48 PM <br/>  
 * @author   gavli  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface ResourceParse extends ResourceLoader{
	
	/**
	 * 
	 * resourceParse : parse the file by the inpustream and switching to JavaBean. <br/>  
	 *  
	 * @author gavli  
	 * @param fileInputStream
	 * @return T
	 * @since JDK 1.6
	 */
	Object resourceParse(FileInputStream fileInputStream) throws IOException;
	
	/**
	 * 
	 * resourceMap: switching the object 2 Map. <br/>  
	 *  
	 * @author gavli  
	 * @param object
	 * @return  
	 * @since JDK 1.6
	 */
	Map<String, Object> resourceMap(Object object);
	
	
	
	

}
  
