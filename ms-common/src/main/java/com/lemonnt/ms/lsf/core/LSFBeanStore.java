/**
 * @author : Gavin Li
 * @date   : Jun 13, 2016
 * @class  :com.cisco.webex.servlet.EagleBeanStore
 */
package com.lemonnt.ms.lsf.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author : Gavin Li
 * @date : Jun 13, 2016
 * @class :com.cisco.webex.servlet.EagleBeanStore
 */
public class LSFBeanStore implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	/**
	 * 
	 * @param applicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		LSFBeanStore.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param name
	 * @return Object
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}

}
