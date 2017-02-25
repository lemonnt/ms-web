/**  
 * Project Name: tahoe-common  
 * File Name: MessageUtils.java  
 * Package Name: com.cisco.webex.frame.exception
 * Date:Dec 7, 201612:06:49 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/

package com.lemonnt.ms.common.exception.core;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * 
 * ClassName: MessageUtils <br/>
 * Function: parse the file of exception <br/>
 * Reason: Improvement the exception logic <br/>
 * date: Dec 7, 2016 10:34:51 AM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6
 */

public class MessageUtils {
	
	/**  
	 * rbs: resourceBundle afford a method to deal with international file
	 * @since JDK 1.6  
	 */
	ResourceBundle[] rbs;

	/**  
	 * Creates a new instance of MessageUtils.  
	 *  
	 * @param baseName - should be a name of file ,like tahoe_en_CN 
	 */  
	public MessageUtils(String baseName) {
		this.rbs = getBundle(baseName);
	}

	/**  
	 * Creates a new instance of MessageUtils.  
	 *  
	 * @param baseName - should be a name of file ,like tahoe_en_CN
	 * @param loader - classLoader
	 */  
	public MessageUtils(String baseName, ClassLoader loader) {
		this.rbs = getBundle(baseName, loader);
	}

	/**  
	 * Creates a new instance of MessageUtils.  
	 *  
	 * @param baseName - should be a name of file ,like tahoe_en_CN
	 * @param locale - A Locale object represents a specific geographical, political, or cultural region. 
	 * @param loader - classLoader
	 */  
	public MessageUtils(String baseName, Locale locale, ClassLoader loader) {
		this.rbs = getBundle(baseName, locale, loader);
	}

	/**  
	 * TODO
	 * @Description: TODO
	 * @author gavli  
	 * @param code - exception code 
	 * @param args - 1001 = Can't ping {1} out .{1} is the arg
	 * @return Getting the exception from a properties file.
	 * @since JDK 1.6  
	 */
	public String getCodeMessage(String code, Object[] args) {
		return getCodeMessage(code, this.rbs, args);
	}
	
	/**  
	 * TODO
	 * @Description: TODO
	 * @author gavli  
	 * @param code
	 * @param args
	 * @return  
	 * @since JDK 1.6  
	 */
	public String getCodeMessage(Integer code, Object[] args) {
		return getCodeMessage(String.valueOf(code), this.rbs, args);
	}

	private String getCodeMessage(String code, ResourceBundle[] rbs, Object[] args) {
		StringBuffer message;
		Object[] arr$;
		int len$;
		int i$;
		Object obj;
		try {
			if (code == null)
				return null;

			String localeMessage = null;
			ResourceBundle[] arr = rbs;
			int len = arr.length;
			for (int i = 0; i < len;) {
				ResourceBundle rb = arr[i];
				try {
					if (rb != null)
						localeMessage = new String(rb.getString(code).getBytes("ISO-8859-1"),"UTF-8");
						//localeMessage = rb.getString(code);
				} catch (MissingResourceException e) {
					e.printStackTrace();
				}
				++i;
			}

			if (localeMessage == null) {
				StringBuilder content = new StringBuilder();
				content.append("Fail to find description for code [");
				content.append(code);
				content.append("]");
				if ((args != null) && (args.length > 0)) {
					arr$ = args;
					len$ = arr$.length;
					for (i$ = 0; i$ < len$; ++i$) {
						obj = arr$[i$];
						content.append(",[");
						content.append(obj);
						content.append("]");
					}
				} else {
					content.append(",args is null or length is 0");
				}
				content.append(".");
				return content.toString();
			}
			if ((args == null) || (args.length <= 0)) {
				return localeMessage;
			}

			localeMessage = replaceArgs(localeMessage, args);
			return localeMessage;
		} catch (Throwable t) {
			message = new StringBuffer();
			message.append("Fail to find description for code [");
			message.append(code);
			message.append("]");
			if ((args != null) && (args.length > 0)) {
				arr$ = args;
				len$ = arr$.length;
				for (i$ = 0; i$ < len$; ++i$) {
					obj = arr$[i$];
					message.append(",[");
					message.append(obj);
					message.append("]");
				}
			} else {
				message.append(",args is null or length is 0");
			}

			message.append(". error :");
			message.append(exToString(t));
		}
		return message.toString();
	}

	public String exToString(Throwable t) {
		StringWriter sw = new StringWriter(5120);
		PrintWriter pw = new PrintWriter(sw);
		try {
			t.printStackTrace(pw);
			String str = sw.toString();
			return str;
		} finally {
			pw.close();
		}
	}

	private String replaceArgs(String s, Object[] args) {
		if ((s == null) || (args == null) || (args.length <= 0))
			return s;

		int i = 0;
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile("\\{(.*?)\\}");
		Matcher m = p.matcher(s);
		while (m.find()) {
			String key = m.group();
			if (args.length > i)
				s = s.replace(key, String.valueOf(args[(i++)]));

		}

		sb.append(s);
		return sb.toString();
	}

	public ResourceBundle[] getBundle(String baseNames) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if (cl == null)
			cl = ClassLoader.getSystemClassLoader();

		return getBundle(baseNames, Locale.getDefault(), cl);
	}

	public ResourceBundle[] getBundle(String baseNames, ClassLoader loader) {
		return getBundle(baseNames, Locale.getDefault(), loader);
	}

	public ResourceBundle[] getBundle(String baseNames, Locale locale, ClassLoader loader) {
		String[] argBaseName = baseNames.split(",");
		ResourceBundle[] rbs = new ResourceBundle[argBaseName.length];
		for (int i = 0,len = argBaseName.length; i < len;++i) {
			String baseName = argBaseName[i];
			try {
				rbs[i] = ResourceBundle.getBundle(baseName, locale, loader);				
			} catch (MissingResourceException e) {
				e.printStackTrace();
			}
		}

		return rbs;
	}
}
