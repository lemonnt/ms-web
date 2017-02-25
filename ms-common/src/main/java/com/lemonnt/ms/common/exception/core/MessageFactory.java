/**  
 * Project Name: tahoe-common  
 * File Name: MessageFactory.java  
 * Package Name: com.cisco.webex.frame.exception  
 * Date:Dec 7, 201612:06:09 AM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/
package com.lemonnt.ms.common.exception.core;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * ClassName: MessageFactory <br/>
 * Function: create messageUtils instance<br/>
 * Reason:  <br/>
 * date: Dec 7, 2016 10:34:44 AM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6
 */
public class MessageFactory {
	
	private static BlockingQueue<Map<String, MessageUtils>> messageUtils = new ArrayBlockingQueue<Map<String,MessageUtils>>(100);
	/**  
	 * Getting the instance of MessageUtils by [baseName]
	 * @Description: Getting the instance of MessageUtils by [baseName]
	 * @author gavli  
	 * @param baseName - should be a name of file ,like tahoe_en_CN
	 * @return a instance of MessageUtils
	 * @since JDK 1.6  
	 */
	public static MessageUtils getMessageUtil(String baseName) {
		MessageUtils mUtil = contains(baseName);
		if(mUtil == null){
			mUtil = new MessageUtils(baseName);
			offerMap(baseName, mUtil);
		}
		return mUtil;
	}

	/**  
	 * Getting the instance of MessageUtils by [baseName] and [loader]
	 * @Description: TODO
	 * @author gavli  
	 * @param baseName - should be a name of file ,like tahoe_en_CN
	 * @param loader - classLoader
	 * @return a instance of MessageUtils
	 * @since JDK 1.6  
	 */
	public static MessageUtils getMessageUtil(String baseName, ClassLoader loader) {
		MessageUtils mUtil = contains(baseName);
		if(mUtil == null){
			mUtil = new MessageUtils(baseName, loader);
			offerMap(baseName, mUtil);
		}
		return mUtil;
	}

	/**  
	 * Getting the instance of MessageUtils by [baseName] and [loader] and [locale]
	 * @Description: TODO
	 * @author gavli  
	 * @param baseName - should be a name of file ,like tahoe_en_CN
	 * @param locale - A Locale object represents a specific geographical, political, or cultural region. 
	 * @param loader - classLoader
	 * @return  
	 * @since JDK 1.6  
	 */
	public static MessageUtils getMessageUtil(String baseName, Locale locale, ClassLoader loader) {
		MessageUtils mUtil = contains(baseName);
		if(mUtil == null){
			mUtil = new MessageUtils(baseName, locale, loader);
			offerMap(baseName, mUtil);
		}
		return mUtil;
	}
	
	public static void remove(String baseName){
		MessageUtils messageUtil = contains(baseName);
		if(null != messageUtil) messageUtils.remove(messageUtil);
	}
	
	
	private static MessageUtils contains(String baseName){
		for(Map<String, MessageUtils> mu : messageUtils){
			if(mu.containsKey(baseName)){
				return mu.get(baseName);
			}
		}
		return null;
	}
	
	private static void offerMap(String baseName,MessageUtils messageUtil){
		Map<String, MessageUtils> mapUtil = new HashMap<String, MessageUtils>();
		mapUtil.put(baseName, messageUtil);
		try {
			messageUtils.put(mapUtil);
		} catch (InterruptedException e) {
			e.printStackTrace();  
		}
	}
}
