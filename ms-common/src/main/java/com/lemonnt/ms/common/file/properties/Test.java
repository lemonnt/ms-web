/**  
 * Project Name:ms-common  
 * File Name:Test.java  
 * Package Name:com.lemonnt.ms.common.file.properties  
 * Date:Dec 6, 20163:41:48 PM  
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package com.lemonnt.ms.common.file.properties;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.lemonnt.ms.common.file.ClazzType;
import com.lemonnt.ms.common.file.ElementAnnotations;

/**  
 * ClassName:Test <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     Dec 6, 2016 3:41:48 PM <br/>  
 * @author   gavli  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class Test {

	@ElementAnnotations(value="test",type=ClazzType.Integer)
	private int test;
	
	@ElementAnnotations("test2")
	private String test2;
	
	@ElementAnnotations(value="test3",type=ClazzType.Boolean)
	private boolean flag;
	
	@ElementAnnotations(value="test4",type=ClazzType.List,sign=",:")
	private List<String> list;
	
	@ElementAnnotations(value="test5",type=ClazzType.Map,sign=",:")
	private Map<String, String> map;
	
	
	public int getTest() {
		return test;
	}
	public void setTest(int test) {
		this.test = test;
	}
	public String getTest2() {
		return test2;
	}
	public void setTest2(String test2) {
		this.test2 = test2;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public static void main(String[] args) {
		String test = "XXdsdsdsddsXdsdsdsXX_sfadsdewewesds_fa";
		System.out.println(replace1(test));
		System.out.println(replace2(test));
		System.out.println(replace3(test));
	}
	
	public static String replace1(String value){
		if(null == value || "".equals(value) || !value.contains("_"))return null;
	    String[] values = value.split("_");
	    String result = values[0];
	    for(int i = 1,length = values.length;i <= length-1;i++){
	    	String r = values[i];
	    	result +=r.substring(0, 1).toUpperCase()+r.substring(1);
	    }
	    return result;
	}
	
	public static String replace2(String value){
		if(null == value || "".equals(value) || !value.contains("_"))return null;
		 Pattern pattern = Pattern.compile("_\\w"); 
		 Matcher matcher = pattern.matcher(value); 
		 while(matcher.find()){
			 String temp = value.substring(matcher.start(),matcher.end());
			 value = value.replaceAll(temp, temp.toUpperCase());
		 }
		 return value.replaceAll("_", "");
	}

	public static String replace3(String value){
		if(null == value || "".equals(value) || !value.contains("_"))return null;
		String result = "";
		for(int i = 0,length = value.length();i <= length-1;i++){
			char r = value.charAt(i);
			if(r == 95){
				i++;
				result +=String.valueOf(value.charAt(i)).toUpperCase();
				continue;
			}				
			result +=String.valueOf(r);			
		}
		return result;
	}
	
}
  
