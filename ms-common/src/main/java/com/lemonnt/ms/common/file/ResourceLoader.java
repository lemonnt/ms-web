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
import java.io.FileNotFoundException;
import java.io.IOException;

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
public interface ResourceLoader {
	/**
	 * 
	 * resourceName : getting the name of file. <br/>  
	 *  
	 * @author gavli  
	 * @return java.lang.String
	 * @since JDK 1.6
	 */
	String resourceName();
	
	
	/**
	 * 
	 * resourceStream : getting the fileinputstream for the file. <br/>  
	 *  
	 * @author gavli  
	 * @return java.io.InputStream
	 * @since JDK 1.6
	 */
	FileInputStream resourceStream(String path) throws IOException,FileNotFoundException;
	

	/**
	 * 
	 * resourceType: getting the file type. <br/>  
	 *  
	 * @author gavli  
	 * @return ResourceType
	 * @since JDK 1.6
	 */
	ResourceType resourceType();
	
}
  
