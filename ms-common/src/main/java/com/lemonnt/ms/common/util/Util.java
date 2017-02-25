/**
 * @author : Gavin Li
 * @date : Oct 26, 2016
 * @version : 1.0
 * @class :com.lemonnt.ms.common.util.Util
 */
package com.lemonnt.ms.common.util;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.DigestUtils;



/**
 * @author : Gavin Li
 * @date : Oct 26, 2016
 * @version : 1.0
 * @class :com.lemonnt.ms.common.util.Util
 */
public class Util{
    public static boolean isEmpty(Object object) {
        return null == object || object.equals("");
    }
    
    public static String createMessageCode() {
        String code = "";
        for (int i = 0; i <= 5; i++) {
            code +=new Random().nextInt(10);
        }
        return code;
    }
    
    public static List<String> switchStr2List(String value,String sign){
        if(isEmpty(value) || isEmpty(sign)) return new ArrayList<String>();
        String[] result = value.split(sign);
        return Arrays.asList(result);
    }
    
    public static String buildCondition4InStr(String condition,String sign){
        List<String> result = switchStr2List(condition, sign);
        String conditions = "";
        for(String r :result){
            conditions +="'"+r+"',";
        }
        if("".equals(conditions)) return null;
        if(conditions.endsWith(",")) return conditions.substring(0,conditions.length()-1);
        return conditions;
        
    }
    
    public static String buildCondition4InNumber(String condition,String sign){
        List<String> result = switchStr2List(condition, sign);
        String conditions = "";
        for(String r :result){
            conditions +=r+",";
        }
        if("".equals(conditions)) return null;
        if(conditions.endsWith(",")) return conditions.substring(0,conditions.length()-1);
        return conditions;
        
    }

    public static String md5(String token,String sid,String time){
        String result = sid+token+time;
        return DigestUtils.md5Hex(result);
        
    }
    
    public static String formatPrint(String intro, String symbol, int length) {
        if (null == intro)
            intro = "Loading";
        if (length % 2 != 0)
            length++;
        int introLength = intro.length() + 2;
        if (introLength % 2 != 0)
            introLength++;
        if (length < introLength)
            length = introLength;
        int halfLength = (length - intro.length()) / 2;
        String upSymbol = "", downSymbol = "", result = "";
        for (int i = 0; i <= length; i++) {
            upSymbol += symbol;
        }
        for (int i = 2; i <= halfLength; i++) {
            downSymbol += " ";
        }
        if (intro.length() % 2 == 0)
            result = "\n" + upSymbol + "\n" + symbol + downSymbol + intro + downSymbol + " " + symbol + "\n" + upSymbol;
        else
            result = "\n" + upSymbol + "\n" + symbol + downSymbol + intro + downSymbol + "  " + symbol + "\n"
                    + upSymbol;

        return result;

    }
    
    public static String ipAddress(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "localhost";
        } 
    }
    
    public static Integer byteLength(String value){
        if(isEmpty(value)) return 0;
        return value.getBytes().length;
    }

    public static boolean matchDate(String date){
        String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(date);
        boolean dateFlag = m.matches();
        if (!dateFlag) {
            return false;
        }
        return true;
    }
    
    public static boolean match(String str){      
        return Pattern.compile("^\\d+$").matcher(str).find();
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
	
    
    
    public static void main(String[] args) {
		System.out.println(new File("c:/tes/fafaf/faaf").isAbsolute());
	}

}
