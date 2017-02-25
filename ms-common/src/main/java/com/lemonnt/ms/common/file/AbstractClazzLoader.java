/**  
 * Project Name:ms-common  
 * File Name:AbstractClazzLoader.java  
 * Package Name:com.lemonnt.ms.common.file  
 * Date:Dec 6, 20161:39:50 PM  
 * Copyright (c) 2016 All Rights Reserved.  
 *  
*/  
  
package com.lemonnt.ms.common.file;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import com.lemonnt.ms.common.bean.AdvancedFile;
import com.lemonnt.ms.common.exception.InvalidClazzType;
import com.lemonnt.ms.common.exception.UnsupportedFileType;
import com.lemonnt.ms.common.util.Util;


/**  
 * ClassName:AbstractClazzLoader <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     Dec 6, 2016 1:39:50 PM <br/>  
 * @author   gavli  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public abstract class AbstractClazzLoader extends AdvancedFile implements FileClazzLoader,ResourceParse{
	
	private ResourceType resourceType;
	
	private String name;
	
	@Override
	public FileInputStream resourceStream(String path) throws IOException, FileNotFoundException {
		if(!new File(path).isAbsolute()){
			URL url = Thread.currentThread().getContextClassLoader().getResource("");
			path = url.getFile()+path;	
		}
		File file = new File(path);
		if(Util.isEmpty(file)){
			throw new FileNotFoundException("There is no file in the path ["+path+"]");
		}else if(file.isDirectory()){
			throw new FileNotFoundException("There should be a file ,but it is a directory ["+path+"]");
		}
		try {
			ResourceType resourceType = ResourceType.getType(suffix(path));
			if(null == resourceType) throw new UnsupportedFileType("The current type of file was not supported ["+path+"], supported all type of file are following : "+ResourceType.toStrings());
			this.resourceType = resourceType;
			this.name = names(path);
			return new FileInputStream(file);
		} catch (UnsupportedFileType e) {
			throw new IOException("Failed to parse the file ["+e.getMessage()+"]");
		}
	}

	@Override
	public ResourceType resourceType() {
		return resourceType;
	}
	
	
	@Override
	public <T> T bean(Class<T> clazz,String path) throws FileNotFoundException, IOException, InvalidClazzType{	
		Map<String, Object> mapResult = resourceMap(resourceParse(resourceStream(path)));		
	    return switch2JavaBean(clazz, mapResult);

		
	}
	

	@Override
	public <T> T bean(Class<T> clazz, FileInputStream fileInputStream) throws FileNotFoundException, IOException, InvalidClazzType {
		Map<String, Object> mapResult = resourceMap(resourceParse(fileInputStream));	  
	    return switch2JavaBean(clazz, mapResult);
	
	}
	

	@Override
	public String resourceName() {
		return name;
	}
	
	private Map<String, Field> annotations(Class<?> clazz){
		Map<String, Field> resultKey2Fields = new HashMap<String, Field>();
		if(Util.isEmpty(clazz)) return resultKey2Fields;
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			ElementAnnotations elementAnnotations = field.getAnnotation(ElementAnnotations.class);
			String value = elementAnnotations.value();
			if(!Util.isEmpty(value))
				resultKey2Fields.put(value, field);
		}
		return resultKey2Fields;
	}
	
	private ClazzType getResourceType(Field field){
		if(Util.isEmpty(field)) return null;
		ElementAnnotations elementAnnotations = field.getAnnotation(ElementAnnotations.class);
		return elementAnnotations.type();
	}
	
	private String getResourceSign(Field field){
		if(Util.isEmpty(field)) return null;
		ElementAnnotations elementAnnotations = field.getAnnotation(ElementAnnotations.class);
		return elementAnnotations.sign();
	}
	
	private <T> T switch2JavaBean(Class<T> clazz,Map<String, Object> mapResult) throws InvalidClazzType{
		Map<String, Field> key2Fields = annotations(clazz);
		T instance = null;
		try {
			instance = clazz.newInstance();
		} catch (InstantiationException e1) {

		} catch (IllegalAccessException e2) { 
			
		}
		if(Util.isEmpty(instance)) return null;
		for(Map.Entry<String, Object> result : mapResult.entrySet()){
			String key = result.getKey();
			Object value = result.getValue();
			if(key2Fields.containsKey(key)){
				Field field = key2Fields.get(key);
				field.setAccessible(true);
				try {
					ClazzType clazzType = getResourceType(field);
					Object object = ClazzType.switchingValue(clazzType, value);
					if(Util.isEmpty(object)) throw new InvalidClazzType("unsupported clazz type ["+clazzType+"]");
					if(object instanceof ClazzType){
						field.set(instance, switchList(value,field,clazzType));
					}else{
						field.set(instance, object);
					}
					
				} catch (IllegalArgumentException e) {
					
				} catch (IllegalAccessException e) {
					
					
				}
			}
			
		}
		return instance;
	}
	
	private Object switchList(Object value,Field field,ClazzType clazzType){
		if(Util.isEmpty(value)) return null;
		String strValue = String.valueOf(value);
		String sign = getResourceSign(field);
		if(Util.isEmpty(sign)) return null;
		if(clazzType.equals(ClazzType.List)){
			if(sign.length() > 1)
				sign = String.valueOf(sign.charAt(0));
			String[] strValues = strValue.split(sign);
			return Arrays.asList(strValues);
		}else if(clazzType.equals(ClazzType.Map)){
			Map<String, String> resultMap = new HashMap<String, String>();
			String[] strValues = strValue.split(String.valueOf(sign.charAt(0)));
			for(String result : strValues){
				String[] results = result.split(String.valueOf(sign.charAt(1)));
				if(results.length < 2)
					continue;
				resultMap.put(results[0], results[1]);
			}
			return resultMap;
		}
		
		return null;
	}
	
	
}
  
