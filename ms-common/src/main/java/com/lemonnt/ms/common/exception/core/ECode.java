/**  
 * Project Name:ms-common  
 * File Name:ECode.java  
 * Package Name:com.lemonnt.ms.common.exception.core  
 * Date:Dec 13, 20165:38:18 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
/**  
 * Project Name: ms-common  
 * File Name: ECode.java  
 * Package Name: com.lemonnt.ms.common.exception.core  
 * Date: Dec 13, 20165:38:18 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
 */  
  
package com.lemonnt.ms.common.exception.core;

import java.util.Locale;
import com.lemonnt.ms.lsf.bean.DefaultConfiguration;
import com.lemonnt.ms.lsf.bean.LanguageType;
import com.lemonnt.ms.lsf.core.LSFBeanStore;

/**  
 * ClassName: ECode <br/>
 * Function: 异常处理的国际化支持 <br/>
 * Reason: TODO ADD REASON <br/>
 * date: Dec 13, 2016 5:38:18 PM <br/>
 * @author gavli  
 * @version 1.0.0 
 * @since JDK 1.6  
 */
public class ECode implements IECode{
	
	private static final String DEFAULT_NAME = "default";
	private static DefaultConfiguration defaultConfiguration = (DefaultConfiguration) LSFBeanStore.getBean("defaultConfigration");	
	private static  String rootPath = defaultConfiguration.getExceptionRootPath();
	private static  String exceptionLanguage = defaultConfiguration.getExceptionLanguage().toUpperCase();
	private MessageUtils messageUtils =null;
	
	/**  
	 * Creates a new instance of ECode.  
	 *    
	 */

	public ECode(String localePath) {
		init(localePath);
	}
	
	public ECode(){};
	
	public void init(String localePath){
		//DefaultConfiguration defaultConfiguration = new DefaultConfiguration();
		//defaultConfiguration.setExceptionLanguage("us");
		////defaultConfiguration.setExceptionRootPath("config/exception");
		//String rootPath = defaultConfiguration.getExceptionRootPath();
		//String exceptionLanguage = defaultConfiguration.getExceptionLanguage().toUpperCase();
		if(!rootPath.endsWith("/")) rootPath +="/";
		if(null == localePath) localePath = DEFAULT_NAME;
		if(localePath.endsWith(".properties")) localePath = localePath.substring(0, localePath.lastIndexOf("."));
		Locale locale = LanguageType.getLocale(exceptionLanguage);		
		if(null == locale) return;
		if(localePath.endsWith(locale.toString())){			
			messageUtils = MessageFactory.getMessageUtil(rootPath+localePath,locale,RootException.class.getClassLoader());
		}else {
			//if(!localePath.endsWith("_")) localePath +="_";
			if(localePath.contains("_")){
				String[] localePaths = localePath.split("_");
				if(localePaths.length >= 3){
					Locale locale2 = LanguageType.getLocale(localePaths[localePaths.length-1]);	
					if(null != locale2){
						messageUtils = MessageFactory.getMessageUtil(rootPath+localePath,locale2,RootException.class.getClassLoader());
						return;
					}
				}else if(!localePath.endsWith("_")){
					localePath +="_";
				}
			}else if(!localePath.endsWith("_")){
				localePath +="_";
			}
			messageUtils = MessageFactory.getMessageUtil(rootPath+localePath+locale.toString(),locale,RootException.class.getClassLoader());
		}
	}
	public String getCodeMessage(String code, Object... args){
		if(null != messageUtils) return messageUtils.getCodeMessage(code, args);
		return null;
	}
	
	public String getCodeMessage(int code, Object... args){
		if(null != messageUtils) return messageUtils.getCodeMessage(code, args);
		return null;
	}
}
  
