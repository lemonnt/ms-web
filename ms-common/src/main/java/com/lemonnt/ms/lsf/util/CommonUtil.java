/**
 * @author : Gavin Li
 * @date   : Jun 23, 2016
 * @class  :com.cisco.webex.common.servlet.util.CommonUtil
 */
package com.lemonnt.ms.lsf.util;

import java.io.*;
import java.util.*;

import org.springframework.util.StringUtils;

import com.lemonnt.ms.lsf.bean.Constant;
import com.lemonnt.ms.lsf.exception.LSFException;



/**
 * @author : Gavin Li
 * @date   : Jun 23, 2016
 * @class  :com.cisco.webex.common.servlet.util.CommonUtil
 */
public class CommonUtil implements Constant {
	
  public static List<File> scannerFiles(String path){	 
	  List<File> result = new ArrayList<File>();
	  if(null == path)
		  return result;
	  File file = new File(path);
	  if(file.isFile()){
		  if(isProperties(file))
			  result.add(file);
	  }else{
		  File[] files = file.listFiles();
		  if(files != null){
			  for(File f : files){
				  path = f.getAbsolutePath();
				  result.addAll(scannerFiles(path));
			  }
		  }
		  
	  }	  
	return result;
	  
  }
  
  public static boolean isProperties(String filePath) {
      if (StringUtils.isEmpty(filePath)) return false;
      if (filePath.contains(FILE_PATH_PATTERN_RIGHT_SIGN))
          filePath = filePath.replaceAll(FILE_PATH_RIGHT_SIGN, FILE_PATH_LEFT_SIGN);
      String filePro = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
      if (StringUtils.isEmpty(filePath)) return false;
      if (PROPERTIES.equals(filePro)) return true;
      return false;
  }
  
  public static boolean isProperties(File file) {
	  String filePath = file.getAbsolutePath();
      return isProperties(filePath);
  }
  
  public static String getFileName(String filePath) {
      if (StringUtils.isEmpty(filePath)) return null;
      if (filePath.contains(FILE_PATH_PATTERN_RIGHT_SIGN))
          filePath = filePath.replaceAll(FILE_PATH_RIGHT_SIGN, FILE_PATH_LEFT_SIGN);
      return filePath.substring(filePath.lastIndexOf(FILE_PATH_LEFT_SIGN) + 1,
          filePath.lastIndexOf("."));
  }
  
  public static String getFileName(File file) {
	  String filePath = file.getAbsolutePath();
	  return getFileName(filePath);
  }
  
  public static Properties parseProperties(String file){
      File f = new File(file);
      Properties properties = new Properties();
      InputStream fileInputStream = null;
      try {
          if (!f.isFile()) throw new LSFException("Error filetype {} ");
          fileInputStream = new FileInputStream(file);
          BufferedReader bf = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
          properties.load(bf);
      } catch (IOException e) {
          throw new LSFException("Failed to parse properties []");
      } finally {
          if (null != fileInputStream) try {
              fileInputStream.close();
          } catch (IOException e) {
        	  throw new LSFException("Failed to close fileInputStream []");
          }
      }
      return properties;
  }
  
  public static Map<String, Object> loadProperties(String path){
	  Map<String, Object> result = new HashMap<String, Object>();
	  Properties properties = parseProperties(path);
	  Set<Object> keys = properties.keySet();
	  for(Object o : keys){
		 Object  object = properties.get(o);
		 result.put(String.valueOf(o), object);
	  }
	  return result;
	 
  }
  
  public static Map<String, Object> loadProperties(File file){
	  if(!file.isFile())
		  return new HashMap<String, Object>();
	  String path = file.getAbsolutePath();
	  Map<String, Object> result = new HashMap<String, Object>();
	  Properties properties = parseProperties(path);
	  Set<Object> keys = properties.keySet();
	  for(Object o : keys){
		 Object  object = properties.get(o);
		 result.put(String.valueOf(o), object);
	  }
	  return result;
	 
  }
  
  public static void main(String[] args) {
	  List<File> files = scannerFiles("E:\\lemonnt\\lemonnt-1.0\\java\\company-lemonnt\\lemonnt-web\\src\\main\\webapp\\WEB-INF\\conf\\language\\common\\");
	  for(File file : files){
		  System.out.println(getFileName(file));
	  }

}

}
