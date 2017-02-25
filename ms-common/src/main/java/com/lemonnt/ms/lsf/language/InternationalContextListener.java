package com.lemonnt.ms.lsf.language;

import java.io.File;
import java.util.*;
import javax.servlet.*;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import com.lemonnt.ms.lsf.bean.*;
import com.lemonnt.ms.lsf.core.*;
import com.lemonnt.ms.lsf.util.CommonUtil;

public class InternationalContextListener extends InternationalLanguage implements ServletContextListener,Constant {
	private static final Logger logger = Logger.getLogger(InternationalContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		languages  = null;
		
	}

	@Override
	public void contextInitialized(final ServletContextEvent sce) {

		 try {
	            new CallBack("InternationalContextListener").record(new ICallBack() {
					public void recordProcedureRunTime() {
	                    ServletContext servletContext = sce.getServletContext();
	                    String init_params = servletContext.getInitParameter("language");
	                    if(StringUtils.isEmpty(init_params))
	                    	init_params = DEFAULT_LANGUAGE_PATH;
	                    String rootPath = servletContext.getRealPath("WEB-INF/");
	                    String path = rootPath+"/"+init_params;
	                    if (path.contains(FILE_PATH_PATTERN_RIGHT_SIGN))
	                    	path =path.replaceAll(FILE_PATH_RIGHT_SIGN,FILE_PATH_LEFT_SIGN);
	                    if(path.contains(FILE_PATH_LEFT_DUBBLE_SIGN)){
	                    	path = path.replaceAll(FILE_PATH_LEFT_DUBBLE_SIGN, FILE_PATH_LEFT_SIGN);
	                    }
	                    languages = parseLanguage(path);                   
	                }
	            });
	            
	        } catch (Exception e) {
	            logger.error("Failed to load properties", e);
	        }
	}
	

	private List<Language> parseLanguage(String path){
		List<File> files = CommonUtil.scannerFiles(path);
		logger.info("Language files including : "+files.toString());
		List<Language> languages = new ArrayList<Language>();
		Map<String, List<File>> classfyFileByLanguage = new HashMap<String, List<File>>(); 
		for(File file : files){			
			String fileName = CommonUtil.getFileName(file);
			if(!fileName.contains("_")){
				logger.warn("Invalid language file ["+fileName+"]");
				continue;				
			}				
			fileName = fileName.substring(fileName.lastIndexOf("_")+1,fileName.length());			
			if(classfyFileByLanguage.containsKey(fileName)){
				classfyFileByLanguage.get(fileName).add(file);
			}else{
				List<File> kindFiles = new ArrayList<File>();
				kindFiles.add(file);
				classfyFileByLanguage.put(fileName, kindFiles);
			}
		}
		
		for(Map.Entry<String, List<File>> language : classfyFileByLanguage.entrySet()){
			Language outer = new Language();
			outer.setKind(language.getKey());
			for(File file : language.getValue()){
				Language inner = new Language();
				String kind = CommonUtil.getFileName(file);
				kind = kind.substring(0,kind.lastIndexOf("_"));
				kinds.add(kind);
				inner.setKind(kind);
				Map<String, Object> map = CommonUtil.loadProperties(file);
				inner.setProperties(map);
				outer.addLanguage(kind, inner);
			}
			languages.add(outer);			
		}
		return languages;
	}

}
