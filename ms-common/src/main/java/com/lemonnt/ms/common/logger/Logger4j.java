package com.lemonnt.ms.common.logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;

public class Logger4j {
	private static String defaulFilePath ="";
	static
	{
		defaulFilePath = getSystemVar("JAVA_HOME");
	}
	
	/**
	 * 
	 * @param filePath
	 * @param isReWrite
	 * @return
	 * @throws IOException
	 */
    private static String createTempLog4jFile(String filePath,boolean isReWrite,boolean isWrite) throws IOException
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	if(isWrite)
    	{
    		stringBuffer.append("## auto build the log4j.properties\n");
        	stringBuffer.append("log4j.rootLogger=debug, ServerDailyRollingFile, stdout\n");
        	stringBuffer.append("log4j.appender.ServerDailyRollingFile=org.apache.log4j.DailyRollingFileAppender\n");
        	stringBuffer.append("log4j.appender.ServerDailyRollingFile.DatePattern='.'yyyy-MM-dd\n");
        	stringBuffer.append("log4j.appender.ServerDailyRollingFile.File=./logs/logger.log\n");
        	stringBuffer.append("log4j.appender.ServerDailyRollingFile.layout=org.apache.log4j.PatternLayout\n");
        	stringBuffer.append("log4j.appender.ServerDailyRollingFile.layout.ConversionPattern=%d - %m%n\n");
        	stringBuffer.append("log4j.appender.ServerDailyRollingFile.Append=true\n\n");
        	stringBuffer.append("log4j.appender.stdout=org.apache.log4j.ConsoleAppender\n");
        	stringBuffer.append("log4j.appender.stdout.layout=org.apache.log4j.PatternLayout\n");
        	stringBuffer.append("log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %p [%c] %m%n");  	
        	if(null == filePath)
        		throw new IOException("FileNotFind");
        	FileOutputStream out = new FileOutputStream(filePath,isReWrite);
        	out.write(stringBuffer.toString().getBytes()); 
        	out.close();
    	}    	
    	return filePath;
    }
    
    /**
	 * @function  create a new filePath by the default's ways
	 * @param fileName the filepath's name
	 * @throws Exception 
	 */
	private static String createFile(boolean isReWrite) throws Exception {
		if(null == defaulFilePath || "".equals(defaulFilePath))
			throw new IOException("There is no variable {JAVA_HOME}");
		File file = new File(defaulFilePath + "/conf");
		// 
		int flag;
		// 
		String fileDir = defaulFilePath + "/conf/log4j.properties";
		if (!file.exists() && !file.isDirectory())
		{
			file.mkdirs();
			flag = buildNewFiles(fileDir);

		} 
		else 
		{
			flag = buildNewFiles(fileDir);
		}

		if (flag == 0) 
		{
			throw new IOException("Failed to build the file");
		} 
		else 
		{
			if(flag == 2 && fileBytes(fileDir) > 0)
			{
				return createTempLog4jFile(fileDir,isReWrite,false);
			}
			else if((flag == 1 || (flag ==2 && fileBytes(fileDir)==0)))
			{
				return createTempLog4jFile(fileDir,isReWrite,true);
			}
			
	
		}
		return "";
	}

	/**
	 * @function  create new file
	 * @param fileName 
	 * @return 
	 * @throws Exception 
	 */
	private static int buildNewFiles(String fileName) throws Exception {

		File filePro = new File(fileName);
		if (!filePro.exists()) 
		{
			try 
			{
				filePro.createNewFile();
			} 
			catch (Exception e) 
			{
			    e.printStackTrace();
				return 0;
			}

		} 
		else {
			//exist
			return 2;
		}
		return 1;
	}
	
	@SuppressWarnings("resource")
	private static Integer fileBytes(String fileName) throws Exception
	{
		FileInputStream fis = new FileInputStream(new File(fileName));
		FileChannel fc = fis.getChannel();
		if (fc.size() > 0)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	
	/**
	 * 
	 * @param key
	 * @return
	 */
	private static String getSystemVar(String key)
	{
		Map<String,String> map = System.getenv();
		for(Map.Entry<String, String> m:map.entrySet())
		{
			if(m.getKey().equals(key))
			{
				return m.getValue();
			}
		}
		return "";
	}
	
	private static void deleteFiles(String filePath)
	{
		int lastIndexof = filePath.lastIndexOf("/");
		filePath =  filePath.substring(0, lastIndexof);
		File file = new File(filePath);
		//delete all file in the current directory
		if(file.isDirectory())
		{
			File[] files = file.listFiles();
			for(int i =0;i <= files.length-1;i++)
			{
				if(files[i].isFile())
				deleteFile(files[i].getAbsolutePath());
				else 
				deleteFiles(files[i].getAbsolutePath());
			}	
		}
		else {
			deleteFile(filePath);
		}
		//delete the directory
		file.delete();
	
	}
	
	private static void deleteFile(String filePath)
	{
		
		File file = new File(filePath);
		if(file.isFile() && file.exists())
			file.delete();
	}
	
	public static void configLogger4j(String log4jPath) throws Exception
	{
		if(null == log4jPath || "".equals(log4jPath))
		{
			String filePath= createFile(false);
			PropertyConfigurator.configure(filePath);
			deleteFiles(filePath);
		}
		else
		{
			PropertyConfigurator.configure(log4jPath);	
		}
		
	}
	
	public static void configLogger4j() 
	{
		String filePath="";
		try {
			filePath = createFile(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PropertyConfigurator.configure(filePath);
		deleteFiles(filePath);
	}
	
	

}
