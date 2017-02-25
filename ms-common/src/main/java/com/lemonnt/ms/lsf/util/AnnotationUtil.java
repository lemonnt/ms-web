/**
 * @author : Gavin Li
 * @date   : Jun 15, 2016
 * @class  :com.cisco.webex.daily.util.PageAnnotationUtil
 */
package com.lemonnt.ms.lsf.util;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lemonnt.ms.lsf.bean.MonitorTime;
import com.lemonnt.ms.lsf.bean.Page;

/**
 * @author : Gavin Li
 * @date   : Jun 15, 2016
 * @class  :com.cisco.webex.daily.util.PageAnnotationUtil
 */
public class AnnotationUtil {
    
    public static String page(Object obj,String... method){       
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        if(null == method || method.length == 0){
            if(null != methods && methods.length > 0){
                for(Method m:methods){
                    if(m.getAnnotation(Page.class) != null)
                        return m.getAnnotation(Page.class).value();
                }
            }
        }else if(method.length == 1){
            for(Method m:methods){
                if(m.getName().equals(method[0]))
                    return m.getAnnotation(Page.class).value();
            }
        }
        
        return null;
    }
    
    public static String page(Method method){  
        Page page = method.getAnnotation(Page.class);
        if(null == page)
            return null;
       return page.value();
    }
    
    public static String monitortime(Method method){  
        MonitorTime monitorTime = method.getAnnotation(MonitorTime.class);
        if(null == monitorTime)
            return null;
       return monitorTime.value();
    }
    
    public static String monitortime(Object bean,Method method){  
        MonitorTime monitorTime = method.getAnnotation(MonitorTime.class);
        if(null == monitorTime)
            return null;
        String value = monitorTime.value();
        if("".equals(value)){
            value = bean.getClass().getName()+" : ["+method.getName()+"]";
        }
            
       return value;
    }
    
    public static boolean isResponseBody(Method method){
       return method.getAnnotation(ResponseBody.class) == null;
    }
    
    public static String requestMapping(Method method){  
        String[] annotions = method.getAnnotation(RequestMapping.class).value();
        if(null != annotions && annotions.length >= 1)
            return annotions[0];
        if(null != annotions)
            throw new RuntimeException("Error requestMapping");
        return null;
     }
    
    public static String requestMapping(Class<?> clazz){ 

        String[] annotations = clazz.getAnnotation(RequestMapping.class).value();
        if(null != annotations && annotations.length >= 1)
            return annotations[0];
        if(null == annotations)
            throw new RuntimeException("Error requestMapping");
        return null;
     }
    
    
    public static String requestMapping(Object obj,String... method){       
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        if(null == method || method.length == 0){
            if(null != methods && methods.length > 0){
                for(Method m:methods){
                    if(m.getAnnotation(Page.class) != null)
                        return m.getAnnotation(Page.class).value();
                }
            }
        }else if(method.length == 1){
            for(Method m:methods){
                if(m.getName().equals(method[0]))
                    return m.getAnnotation(Page.class).value();
            }
        }
        
        return null;
    }
    

}
