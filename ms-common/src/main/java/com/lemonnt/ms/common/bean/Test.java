/**  
 * Project Name:ms-common  
 * File Name:Test.java  
 * Package Name:com.lemonnt.ms.common.bean  
 * Date:Dec 14, 20169:38:45 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-common  
 * File Name: Test.java  
 * Package Name: com.lemonnt.ms.common.bean  
 * Date: Dec 14, 20169:38:45 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.common.bean;

import com.lemonnt.ms.common.exception.MatcherException;

/**  
 * ClassName: Test <br/>
 * Function: TODO ADD FUNCTION <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 14, 2016 9:38:45 AM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public class Test {
	
	@Matcher(expression=RegExpression.NotNull)
	private String name;
	
	@Matcher("^1[3|4|5|7|8]\\d{9}$")
	private String phoneNumber;
	
	
	@Matcher("![,9.0]")
	private Integer number1;
	
	@Matcher("![6,9.89]")
	private Double double1;
	
	/*@Matcher("!>=45")
	private int number2;
	
	@Matcher("!<=56")
	private int number3;
	
	@Matcher("!<56")
	private int number4;
	
	@Matcher("!>=56")
	private double number5;
	
	@Matcher("!>56")
	private double number6;
	
	@Matcher("!<=56")
	private double number7;
	
	@Matcher("!<56")
	private double number8;
	
	@Matcher("!===123")
	private String equals;
	
	@Matcher("!==123")
	private int equal;
	
	@Matcher("!==123")
	private double equal2;
	
	@Matcher("!==123")
	private Double equal3;
	*/
	
	public static void main(String[] args) {
		Test test = new Test();
		test.name = "ew";
		test.phoneNumber = "13865756960";
		test.number1=8;
		test.double1=9.89;
		AdvancedMatcher advancedMatcher = new AdvancedMatcher();
		
		try {
			advancedMatcher.match(test);
			String value = "32323";
			test(value);
			System.out.println(value);
			
		} catch (MatcherException e) {
			  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
			
		}
	}

	
	public static void test(String vaule){
		vaule = "133";
	}
}
  
