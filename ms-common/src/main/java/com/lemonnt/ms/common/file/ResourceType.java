/**  
 * Project Name:ms-common  
 * File Name:Type.java  
 * Package Name:com.lemonnt.ms.common.file  
 * Date:Dec 6, 201612:19:03 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
  
package com.lemonnt.ms.common.file;

import com.lemonnt.ms.common.util.Util;

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
public enum ResourceType {
	/**
	 * supported the type of file
	 */
	XML("xml"),PROPERTIES("properties"),CSV("csv"),TXT("txt"),CONFIG("config");
	
	/**
	 * @param the type of file
	 */
	private String value;
	
	
    ResourceType(String value){
		this.value = value;
	}
   
    public static ResourceType forName(String value) {
        if (!Util.isEmpty(value)) {
        	value = value.trim();
        }
        return ResourceType.valueOf(value);
       
    }
    
    public static ResourceType getType(String value){
    	if(Util.isEmpty(value)) return null;
    	if(value.equals(ResourceType.PROPERTIES.getValue()))
    		return ResourceType.PROPERTIES;
    	if(value.equals(ResourceType.XML.getValue()))
    		return ResourceType.XML;
    	if(value.equals(ResourceType.TXT.getValue()))
    		return ResourceType.PROPERTIES;
    	if(value.equals(ResourceType.CSV.getValue()))
    		return ResourceType.XML;
    	return null;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String toStrings(){
		return "xml,properties,csv,txt,config";
	}

}
  
