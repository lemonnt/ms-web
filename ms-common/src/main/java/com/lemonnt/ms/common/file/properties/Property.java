/**  
 * Project Name:ms-common  
 * File Name:Property.java  
 * Package Name:com.lemonnt.ms.common.file.properties  
 * Date:Dec 6, 20161:32:05 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
  
package com.lemonnt.ms.common.file.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.lemonnt.ms.common.exception.InvalidClazzType;
import com.lemonnt.ms.common.logger.Logger4j;

/**  
 * ClassName:Property <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     Dec 6, 2016 1:32:05 PM <br/>  
 * @author   gavli  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class Property extends PropertyLoader{

	public <T> T bean(Class<T> clazz,String path){
		try {
			return super.bean(clazz, path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}catch (InvalidClazzType e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	public Map<String, Object> map(String path){
		try {
			return super.resourceMap(resourceParse(path));
		} catch (IOException e) {
			  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			Logger4j.configLogger4j("C:/cisco/sources/management.system/ms-web/src/main/webapp/WEB-INF/conf/properties/log4j.properties");
		} catch (Exception e) {
			  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
			
		}
		Property property = new Property();
		//property.map("C:\\cisco\\test.properties");
		Test test = property.bean(Test.class, "C:\\cisco\\test.properties");
		System.out.println(test);
	}
	
}
  
