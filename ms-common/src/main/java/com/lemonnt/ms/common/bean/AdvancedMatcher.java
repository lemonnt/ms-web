/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.AdvancedMatcher
 */
package com.lemonnt.ms.common.bean;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lemonnt.ms.common.exception.MatcherException;
import com.lemonnt.ms.common.util.Util;

/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.AdvancedMatcher
 */
public class AdvancedMatcher implements Constant{
    
    private String wasChecked;
    
    private RegExpression regExpression;
    
    private boolean matcher = Boolean.FALSE;
    
    public AdvancedMatcher(){};
    /**
     * 
     */
    public AdvancedMatcher(RegExpression regExpression) {
        this.regExpression = regExpression;

    }
    
    public AdvancedMatcher(String wasChecked,RegExpression regExpression) {
        this.matcher = match(wasChecked, regExpression);
    }
    
    /**
     * 
     */
    public AdvancedMatcher(String wasChecked) {
        this.wasChecked = wasChecked;
    }
    
    public boolean match(){
        return matcher;
    }
    
    
    public boolean match(RegExpression regExpression){
        return match(wasChecked, regExpression);
    }
    
    public boolean match(String wasChecked){
        return match(wasChecked,regExpression);
    }
    
    public boolean match(String wasChecked,RegExpression regExpression){  
        if(Util.isEmpty(wasChecked)) return false;
        Pattern p = Pattern.compile(regExpression.getName());
        Matcher m = p.matcher(wasChecked);
        return m.matches();
    }
    
    public boolean match(String wasChecked,String regExp){   
        if(Util.isEmpty(wasChecked)) return false;
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(wasChecked);
        return m.matches();
    }
  
    
    /**
     * @return the wasChecked
     */
    public String getWasChecked() {
        return wasChecked;
    }

    /**
     * @param wasChecked the wasChecked to set
     */
    public void setWasChecked(String wasChecked) {
        this.wasChecked = wasChecked;
    }

    /**
     * @return the regExpression
     */
    public RegExpression getRegExpression() {
        return regExpression;
    }

    /**
     * @param regExpression the regExpression to set
     */
    public void setRegExpression(RegExpression regExpression) {
        this.regExpression = regExpression;
    }
    
    /**
     * 
     * TODO
     * @Description: 1.the expression was supported is following : > >= < <= == ===  
     *               and all expression must start with '!' ,like !>=5
     *               2. supported RegExpression
     *               3. supported RegExp
     * @author gavli  
     * @param clazz  
     * @throws MatcherException 
     * @since JDK 1.6
     */
    public void match(Object object) throws MatcherException{
    	Class<?> clazz = object.getClass();
    	Field[] fields = clazz.getDeclaredFields();
    	for(Field field : fields){
    		field.setAccessible(true);
    		com.lemonnt.ms.common.bean.Matcher matcher = field.getAnnotation(com.lemonnt.ms.common.bean.Matcher.class);
    		if(null == matcher) continue;
    		String value = matcher.value();
    		if(!Util.isEmpty(value)){
    			try {
					Object obj = field.get(object);
					String exceptionResult = field.getName()+"="+obj;
					//> --> 62 < --> 60 = -->61
					//1. >=  2. >  3. <=  4. <  5.== 6. ===
					if(value.startsWith("!")){
						if(value.indexOf(",") > 0 && value.startsWith("![") && value.endsWith("]")){						    
							String realValue = obj == null ? null :String.valueOf(obj);
							if(null == realValue) throw new MatcherException(5002, exceptionResult,value.replaceAll("!", ""));
							realValue = realValue.toLowerCase();
							realValue = realValue.replaceAll("l", "").replaceAll("d", "").replaceAll("f", "");
							String[] values = value.split(",");
							if(values.length != 2) throw new MatcherException(5001, value);
							String one = values[0].replaceAll("!\\[", ""),two = values[1].replaceAll("\\]", "");
							if(match(realValue,RegExpression.NUMBER)){
								Long longRealValue = Long.valueOf(realValue);
								one = "".equals(one) ? one : one.contains(".") ? one.substring(0, one.lastIndexOf(".")) : one;
								two = "".equals(two) ? two : two.contains(".") ? two.substring(0, two.lastIndexOf(".")) : two;
								if(match(one, RegExpression.NUMBER) && match(two, RegExpression.NUMBER)){					
									if(longRealValue < Long.valueOf(one) || longRealValue > Long.valueOf(two))
										throw new MatcherException(5002, exceptionResult,value.replaceAll("!", ""));			
								}else if("".equals(one) && match(two,  RegExpression.NUMBER)){
									if(longRealValue > Long.valueOf(two))
										throw new MatcherException(5002, exceptionResult,value.replaceAll("!", ""));	
								}else if("".equals(two) && match(one , RegExpression.NUMBER)){
									if(longRealValue < Long.valueOf(one))
										throw new MatcherException(5002, exceptionResult,value.replaceAll("!", ""));	
								}else{
									throw new MatcherException(5001, value);
								}
							}else if(match(realValue,RegExpression.DOUBLE)){
								Float floatRealValue = Float.parseFloat(realValue);
								one = "".equals(one) ? one : one.contains(".") ? one : one+".00";
								two = "".equals(two) ? two : two.contains(".") ? two : two+".00";
								if(match(one, RegExpression.DOUBLE) && match(two, RegExpression.DOUBLE)){
									if(floatRealValue < Float.parseFloat(one) || floatRealValue > Float.parseFloat(two))
										throw new MatcherException(5002, exceptionResult,value.replaceAll("!", ""));
								}else if("".equals(one) && match(two,  RegExpression.DOUBLE)){
									if(floatRealValue > Float.parseFloat(two))
										throw new MatcherException(5002, exceptionResult,value.replaceAll("!", ""));	
								}else if("".equals(two) && match(one , RegExpression.DOUBLE)){
									if(floatRealValue < Float.parseFloat(one))
										throw new MatcherException(5002, exceptionResult,value.replaceAll("!", ""));	
								}else{
									throw new MatcherException(5001, value);
								}
							}else{
								throw new MatcherException(5001, value);
							}
						}else{
							throw new MatcherException(5001, value);
						}
					}else if(!match(obj == null ? null :String.valueOf(obj), value)){
						throw new MatcherException(5000, exceptionResult,value);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
    		}
    		RegExpression regExpression = matcher.expression();
    		if(!Util.isEmpty(regExpression)){
    			try {
					Object obj = field.get(object);
					if(!match(obj == null ? null :String.valueOf(obj), regExpression)){
						throw new MatcherException(5000, field.getName()+"="+obj,regExpression.getName());
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
    		}
    		field.setAccessible(false);
    	}
    }
    
    public static void main(String[] args) {
    	String value = "0.00";
    	System.out.println(new AdvancedMatcher().match(value, RegExpression.DOUBLE));
	}

}
