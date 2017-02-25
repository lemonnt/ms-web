package com.lemonnt.ms.lsf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class SimpleDateFormatUtil {
		
	private static Logger logger = Logger.getLogger(SimpleDateFormatUtil.class);

	public static SimpleDateFormat getFormatShort() {

		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public static SimpleDateFormat getFormatFull() {

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public static String parseStrToSpecialDate(String str){
		try {
			Date date = getFormatShort().parse(str);
			String day = String.format("%td", date);
			String month = String.format("%tb", date);
			String year = String.format("%ty", date);
			return day+"-"+month+"-"+year;
		} catch (ParseException e) {
			logger.error("Parse date " + str +" error: "+ e.getMessage());
			return null;
		}
	}
}
