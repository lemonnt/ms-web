package com.lemonnt.ms.common.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdvancedDate extends Date{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static String DEFAULT_TYPE_MILL = "yyyy-MM-dd HH:mm:ss.SSS";
	public static String DEFAULT_TYPE = "yyyy-MM-dd HH:mm:ss";
	public static String DEFAULT_TYPE_DD = "yyyy-MM-dd";

	public String formatter(String timeType, long times) {
		return new SimpleDateFormat(timeType).format(times);
	}

	public long toMillisecond(String timeType, String time) {
	    long ms = 0L;
		try {
            ms = new SimpleDateFormat(timeType).parse(time).getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Failed parse the time ["+time+"] with the type ["+timeType+"]");
        }
		return ms;
	}
	
	public String formatter(String formatter) {
		return formatter(formatter, System.currentTimeMillis());
	}

	public String formatter(Date date,String formatter) {
		return formatter(formatter, date.getTime());
	}	

	public Date formatter(String formatter,String date){
        try {
            return new Date(new SimpleDateFormat(formatter).parse(date).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse time");
        }
    }
	
    public int getYear(Date date) {
        return addDate(date, Calendar.YEAR);
    }
    
    public int getMonth(Date date) {
        return addDate(date, Calendar.MONTH)+1;
    }
    
    public int getDay(Date date) {
        return addDate(date, Calendar.DATE);
    }
    
    public int getHour(Date date) {
        return addDate(date, Calendar.HOUR);
    }
      
    public int getMinute(Date date) {
        return addDate(date, Calendar.MINUTE);
    }    
    
    public int getSecond(Date date) {
        return addDate(date, Calendar.SECOND);
    }
    
    public int getMillisecond(Date date) {
        return addDate(date, Calendar.MILLISECOND);
    }
    
    private final int addDate(Date date,int index){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(index);
    }

    public final Date addDate(Date date, int len,int index) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(index, len);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    public Date addMonth(Date date,int len){ 
        return addDate(date, len, Calendar.MONTH);
    }
    
    public Date addDay(Date date,int len){ 
        return addDate(date, len, Calendar.DATE);
    }
    
    public Date addHour(Date date,int len){ 
        return addDate(date, len, Calendar.HOUR);
    }
    
    public Date addMinute(Date date,int len){ 
        return addDate(date, len, Calendar.MINUTE);
    }
    
    public Date addSecond(Date date,int len){ 
        return addDate(date, len, Calendar.SECOND);
    }
    
    public boolean isLastDayForMonth(Date date) {
        Integer day = this.getDay(date);
        Integer lastDay = this.getDay(getLastDayOfMonth(date));
        if (day == lastDay) return true;
        return false;
    }

    public Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

	public int getYear() {
		return getYear(this);
	}

	public int getMonth() {
		return getMonth(this);
	}

	public int getDay() {
		return getDay(this);
	}

	public int getHour() {
		return getHour(this);
	}

	public int getMinute() {
		return getMinute(this);
	}

	public int getSecond() {
		return getSecond(this);
	}

	public int getMillisecond() {
		return getMillisecond(this);
	}

	public String date() {
		return formatter(DEFAULT_TYPE);
	}

	public String toString() {
		return formatter(DEFAULT_TYPE_MILL);
	}
}
