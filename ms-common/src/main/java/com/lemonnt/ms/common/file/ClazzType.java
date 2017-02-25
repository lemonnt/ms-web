/**  
 * Project Name:ms-common  
 * File Name:Type.java  
 * Package Name:com.lemonnt.ms.common.file  
 * Date:Dec 6, 201612:19:03 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
  
package com.lemonnt.ms.common.file;


/**  
 * ClassName:Type <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     Dec 6, 2016 12:19:03 PM <br/>  
 * @author   gavli  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public enum ClazzType {
	String,Double,Float,Integer,Long,Boolean,List,Map;
	
	public static Object  switchingValue(ClazzType clazzType,Object obj){
		if(clazzType.equals(ClazzType.String))
			return java.lang.String.valueOf(obj);
		
		if(clazzType.equals(ClazzType.Integer))
			return java.lang.Integer.valueOf(java.lang.String.valueOf(obj));
		
		if(clazzType.equals(ClazzType.Double))
			return java.lang.Double.valueOf(java.lang.String.valueOf(obj));
			
		if(clazzType.equals(ClazzType.Float))
			return java.lang.Float.valueOf(java.lang.String.valueOf(obj));
		
		if(clazzType.equals(ClazzType.Long))
			return java.lang.Long.valueOf(java.lang.String.valueOf(obj));
		
		if(clazzType.equals(ClazzType.Boolean))
			return java.lang.Boolean.valueOf(java.lang.String.valueOf(obj));
		
		if(clazzType.equals(ClazzType.Boolean))
			return java.lang.Boolean.valueOf(java.lang.String.valueOf(obj));
		
		if(clazzType.equals(ClazzType.List) || clazzType.equals(ClazzType.Map)){
			return clazzType;
		}
		return null;
	}
   
}
  
