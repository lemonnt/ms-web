/**  
 * Project Name:ms-common  
 * File Name:ClazzLoader.java  
 * Package Name:com.lemonnt.ms.common.file  
 * Date:Dec 6, 20161:22:01 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
  
package com.lemonnt.ms.common.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.lemonnt.ms.common.exception.InvalidClazzType;

/**  
 * ClassName:FileClazzLoader <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     Dec 6, 2016 1:22:01 PM <br/>  
 * @author   gavli  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface FileClazzLoader {
	
	/**
	 * 
	 * bean: switch a file 2 bean. <br/>  
	 *  
	 * @author gavli  
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException  
	 * @since JDK 1.6
	 */
	<T> T bean(Class<T> clazz,String path) throws FileNotFoundException, IOException, InvalidClazzType;
	
	
	/**
	 * 
	 * bean: switching fileinputstream 2 javabean <br/>  
	 *  
	 * @author gavli  
	 * @param clazz
	 * @param fileInputStream
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException  
	 * @since JDK 1.6
	 */
	<T> T bean(Class<T> clazz,FileInputStream fileInputStream) throws FileNotFoundException, IOException, InvalidClazzType;
	
	
	
	
}
  
