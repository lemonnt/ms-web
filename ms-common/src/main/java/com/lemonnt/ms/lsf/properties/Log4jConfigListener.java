/**
 * @author : Gavin Li
 * @date   : Jun 8, 2016
 * @class  :com.cisco.webex.properties.Log4jConfigListener
 */
package com.lemonnt.ms.lsf.properties;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.util.StringUtils;

/**
 * @author Gavin Li
 *
 */
public class Log4jConfigListener extends org.springframework.web.util.Log4jConfigListener{

    /* (non-Javadoc)
     * @see org.springframework.web.util.Log4jConfigListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        //log4j
        ServletContext servletContext = event.getServletContext();
        String init_params = servletContext.getInitParameter("log4jEnvSupport");
        if(StringUtils.isEmpty(init_params)){
            setLog4jPath();
        }else{
            setLog4jPath(init_params);
        }
        
        super.contextInitialized(event);
    }
    
    private final void setLog4jPath(String env){
        Map<String, String> map = System.getenv();
        System.setProperty(env, map.get(env));
    }
    
    private final void setLog4jPath(){
        Map<String, String> map = System.getenv();
        for(Map.Entry<String, String> m :map.entrySet()){
            System.setProperty(m.getKey(), m.getValue());
        }
       
    }
    

}
