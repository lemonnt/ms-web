package com.lemonnt.ms.lsf.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Dates {
	public static String DEFAULT_TYPE_MILL = "yyyy-MM-dd HH:mm:ss.SSS";
	public static String DEFAULT_TYPE = "yyyy-MM-dd HH:mm:ss";
	public static Long oneDayMils = 24*60*60*1000L;
	public static Long oneHourMils = 60*60*1000L;

	public String millisecond2TimeType(String timeType, long times) {
		return new SimpleDateFormat(timeType).format(times);
	}

	public long timeType2Millisecond(String timeType, String time) throws ParseException {
		return new SimpleDateFormat(timeType).parse(time).getTime();
	}

	public Date timeType2Date(String timeType,String time){
	    try {
            return new Date(new SimpleDateFormat(timeType).parse(time).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse time");
        }
	}
	
	public String formatTimeType() {
		return millisecond2TimeType(DEFAULT_TYPE, System.currentTimeMillis());
	}

	public String formatTimeType(Date date) {
		return millisecond2TimeType(DEFAULT_TYPE, date.getTime());
	}
	
	public String formatTimeType(String date) throws ParseException {
		return millisecond2TimeType(DEFAULT_TYPE, timeType2Millisecond(DEFAULT_TYPE, date));
	}
	
	public String getCurrentTime(String timeType) {
		return millisecond2TimeType(timeType, System.currentTimeMillis());
	}

	public String formatTimeType(String timeType,Date date) {
		return millisecond2TimeType(timeType, date.getTime());
	}
	
	public String formatTimeType(String timeType,Long time) {
        return millisecond2TimeType(timeType, time);
    }
	
	public String formatTimeType(String timeType,String date) throws ParseException {
		return millisecond2TimeType(timeType, timeType2Millisecond(timeType, date));
	}

	public int getYear() {
		return new GregorianCalendar().get(Calendar.YEAR);
	}

	public int getMonth() {
		return new GregorianCalendar().get(Calendar.MONTH) + 1;
	}

	public int getDay() {
		return new GregorianCalendar().get(Calendar.DATE);
	}

	public int getHour() {
		return new GregorianCalendar().get(Calendar.HOUR_OF_DAY);
	}

	public int getMinute() {
		return new GregorianCalendar().get(Calendar.MINUTE);
	}

	public int getSecond() {
		return new GregorianCalendar().get(Calendar.SECOND);
	}

	public int getMillisecond() {
		return new GregorianCalendar().get(Calendar.MILLISECOND);
	}

	public String getDate() {
		return getCurrentTime(DEFAULT_TYPE);
	}

	public String toString() {
		return getCurrentTime(DEFAULT_TYPE_MILL);
	}

}
