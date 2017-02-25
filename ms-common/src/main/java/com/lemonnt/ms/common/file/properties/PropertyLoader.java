/**  
 * Project Name:ms-common  
 * File Name:PropertyLoader.java  
 * Package Name:com.lemonnt.ms.common.file.properties  
 * Date:Dec 6, 201612:33:31 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
  
package com.lemonnt.ms.common.file.properties;

import java.io.*;
import java.util.*;

import com.lemonnt.ms.common.exception.InvalidClazzType;
import com.lemonnt.ms.common.file.AbstractClazzLoader;
import com.lemonnt.ms.common.util.Util;

/**  
 * ClassName:PropertyLoader <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     Dec 6, 2016 12:33:31 PM <br/>  
 * @author   gavli  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class PropertyLoader extends AbstractClazzLoader {

	@Override
	public Object resourceParse(FileInputStream fileInputStream) throws IOException {
		 Properties properties = new Properties();
		 properties.load(fileInputStream);
		return properties;
	}
	
	public Object resourceParse(String path) throws IOException {
		 Properties properties = new Properties();
		 properties.load(resourceStream(path));
		return properties;
	}

	@Override
	public Map<String, Object> resourceMap(Object object) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(Util.isEmpty(object)) return result; 
		if(object instanceof Properties){
			Properties properties = (Properties) object;
			for(Object key : properties.keySet()){
				result.put(String.valueOf(key), properties.get(key));
			}
		}
		return result;
	}

	@Override
	public <T> T bean(Class<T> clazz, String path) throws FileNotFoundException, IOException, InvalidClazzType {
		return super.bean(clazz, path);
	}

}
  
