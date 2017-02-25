/**
 * @author Gavin Li
 */
package com.lemonnt.ms.lsf.properties;

import java.io.*;
import java.util.*;

import javax.servlet.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.*;

import com.lemonnt.ms.lsf.bean.Configuration;
import com.lemonnt.ms.lsf.core.CallBack;
import com.lemonnt.ms.lsf.core.ICallBack;

public final class PropertiesContextListener extends PropertyPlaceholderConfigurer
        implements
            ServletContextListener {
    private static Logger logger = Logger.getLogger(PropertiesContextListener.class);
    private final static String SPLIT_SIGN = ";";
    private final static String FILE_PATH_RIGHT_SIGN = "\\\\";
    private final static String FILE_PATH_PATTERN_RIGHT_SIGN = "\\";
    private final static String FILE_PATH_LEFT_SIGN = "/";
    private final static String START_SIGN = "*";
    private final static String FILE_HEADE = "/WEB-INF";
    private final static String DEFAULT_CONFIGURATION_FILE = "configuration";
    private final static String DEFAULT_FILE_TYPE = "properties";

    private static Properties[] localProperty;

    private Properties properties;

    private Properties[] localProperties;

    public void init() {
        logger.info("[" + this.getClass() + " ]- [properties] was initializations  {}");
        setLocalProperties(localProperty);
    }

    @Override
    protected Properties mergeProperties() throws IOException {
        if (null != properties) return properties;
        Properties result = new Properties();

        if (this.localOverride) {
            loadProperties(result);
        }

        if (this.localProperties != null) {
            for (Properties localProp : this.localProperties) {
                CollectionUtils.mergePropertiesIntoMap(localProp, result);
            }
        }

        if (!this.localOverride) {
            loadProperties(result);
        }

        return result;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }

    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
    }

    public Properties[] getLocalProperties() {
        return localProperties;
    }

    public void setLocalProperties(Properties[] localProperties) {
        this.localProperties = localProperties;
    }

    protected java.util.Properties loadProperties(String file) throws FileNotFoundException {
        File f = new File(file);
        java.util.Properties properties = new java.util.Properties();
        FileInputStream fileInputStream = null;
        try {
            if (!f.isFile()) throw new FileNotFoundException("Error filetype {} ");
            fileInputStream = new FileInputStream(f);
            properties.load(fileInputStream);
            logger.info("Successed to load file [" + file + "]");
        } catch (IOException e) {
            logger.warn("Failed to load properties [" + e.getStackTrace()[0] + "]");
        } finally {
            if (null != fileInputStream) try {
                fileInputStream.close();
            } catch (IOException e) {
                logger.warn("Failed to close stream [" + e.getStackTrace()[0] + "]");
            }
        }
        return properties;
    }

    public void contextInitialized(final ServletContextEvent sce) {

        try {
            new CallBack("PropertyPlaceholderConfigurer").record(new ICallBack() {                
                public void recordProcedureRunTime() {
                    ServletContext servletContext = sce.getServletContext();
                    String init_params = servletContext.getInitParameter("proConfirmation");
                    String rootPath = servletContext.getRealPath("WEB-INF");
                    java.util.Properties[] properties = getPropertyFiles(init_params, rootPath);
                    localProperty = properties;
                    
                }
            });
            
        } catch (Exception e) {
            logger.error("Failed to load properties", e);
        }
        

    }

    private java.util.Properties[] getPropertyFiles(String paths, String webInfoPath) {
        List<Properties> results = new ArrayList<Properties>();
        if (!StringUtils.isEmpty(paths)) {
            String[] files = paths.split(SPLIT_SIGN);
            for (int i = 0, length = files.length - 1; i <= length; i++) {
                String file = files[i];
                if (file.startsWith(FILE_HEADE)) {
                    if (!StringUtils.isEmpty(webInfoPath)) {
                        if (webInfoPath.contains(FILE_PATH_PATTERN_RIGHT_SIGN))
                            webInfoPath =webInfoPath.replaceAll(FILE_PATH_RIGHT_SIGN,FILE_PATH_LEFT_SIGN);
                        webInfoPath =webInfoPath.substring(0, webInfoPath.lastIndexOf(FILE_HEADE)) + file;
                        if (!isProperties(webInfoPath)) continue;
                        try {
                            String fileName = getFileName(webInfoPath);
                            if(DEFAULT_CONFIGURATION_FILE.equals(fileName)){
                                Configuration.setProperties(loadProperties(webInfoPath));
                            }
                            if(StringUtils.isEmpty(fileName)) continue;
                            if (fileName.indexOf(START_SIGN) >= 0) {
                                String parentPath = webInfoPath.substring(0,webInfoPath.lastIndexOf(FILE_PATH_LEFT_SIGN));
                                fileName = fileName.replaceAll(FILE_PATH_PATTERN_RIGHT_SIGN+START_SIGN, "");
                                List<String> allFiles = fileScanner(parentPath,fileName);
                                for(int m =0,len = allFiles.size()-1;m <= len;m++){
                                    results.add(loadProperties(allFiles.get(m)));
                                }
                                
                            } else {
                                results.add(loadProperties(webInfoPath));
                            }


                        } catch (FileNotFoundException e) {
                            logger.error("FileNotFound " + webInfoPath, e);
                        }
                    }
                } else {
                    try {
                        if (!isProperties(file)) continue;
                        results.add(loadProperties(file));
                    } catch (FileNotFoundException e) {
                        logger.error("FileNotFound " + webInfoPath, e);
                    }
                }              
            }
            java.util.Properties[] properties = new Properties[results.size()];
            for(int i = 0,length = results.size()-1;i <= length;i++){
                properties[i] = results.get(i);
            }
            return properties;

        }
        return null;
    }

    private final boolean isProperties(String filePath) {
        if (StringUtils.isEmpty(filePath)) return false;
        if (filePath.contains(FILE_PATH_PATTERN_RIGHT_SIGN))
            filePath = filePath.replaceAll(FILE_PATH_RIGHT_SIGN, FILE_PATH_LEFT_SIGN);
        String filePro = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
        if (StringUtils.isEmpty(filePath)) return false;
        if (DEFAULT_FILE_TYPE.equals(filePro)) return true;
        return false;
    }

    private final String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) return null;
        if (filePath.contains(FILE_PATH_PATTERN_RIGHT_SIGN))
            filePath = filePath.replaceAll(FILE_PATH_RIGHT_SIGN, FILE_PATH_LEFT_SIGN);
        return filePath.substring(filePath.lastIndexOf(FILE_PATH_LEFT_SIGN) + 1,
            filePath.lastIndexOf("."));
    }

    private final List<String> fileScanner(String fileStr,String includeStr) {
        List<String> result = new ArrayList<String>();
        if (StringUtils.isEmpty(fileStr)) return null;
        if (fileStr.contains(FILE_PATH_PATTERN_RIGHT_SIGN))
            fileStr = fileStr.replaceAll(FILE_PATH_RIGHT_SIGN, FILE_PATH_LEFT_SIGN);
        if(!fileStr.endsWith(FILE_PATH_LEFT_SIGN))
            fileStr +=FILE_PATH_LEFT_SIGN;
        File file = new File(fileStr);
        String[] files = file.list();
        for(int i =0,length = files.length-1; i <= length;i++){
            String f = files[i];
            if(f.contains(includeStr))
                result.add(fileStr+f);
        }
        return result;

    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        localProperty = null;
        
    }



}
