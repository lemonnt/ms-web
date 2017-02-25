/**  
 * Project Name:tahoe-common  
 * File Name:Language.java  
 * Package Name:com.cisco.webex.bean  
 * Date:Dec 13, 20167:16:44 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: tahoe-common  
 * File Name: Language.java  
 * Package Name: com.cisco.webex.bean  
 * Date: Dec 13, 20167:16:44 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.lsf.bean;

import java.util.Locale;

/**  
 * ClassName: Language <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 13, 2016 7:16:44 PM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public enum LanguageType {
	US("en"),CN("ch");
	/**  
	 * Creates a new instance of Language.  
	 *    
	 */
	
	private String value;

	private LanguageType(String value) {
        this.value = value;

	}
	
	public static LanguageType getLanguage(String value){
		return valueOf(value);
	}
	
	public static Locale getLocale(String value){
		if(null == value) return  null;
		String area = value.toUpperCase(),
			   languageType = LanguageType.getLanguage(area).getValue();
		return new Locale(languageType, area);
	}
	
	public String getValue(){
		return value;
	}
	
	public static void main(String[] args) {
		Locale locale = LanguageType.getLocale("US");
		System.out.println(locale.toString());
	}

}
  
