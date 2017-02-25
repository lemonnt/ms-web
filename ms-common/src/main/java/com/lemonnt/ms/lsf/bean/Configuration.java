/**
 * @author : Gavin Li
 * @date   : Oct 30, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.lsf.bean.Configuration
 */
package com.lemonnt.ms.lsf.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.springframework.util.StringUtils;

import com.lemonnt.ms.common.util.Util;

/**
 * @author : Gavin Li
 * @date   : Oct 30, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.lsf.bean.Configuration
 */
public class Configuration {
    
    private static Properties property;
    
    private static BlockingQueue<Map<String, List<String>>> values = new ArrayBlockingQueue<Map<String,List<String>>>(1000);
    
    public static String getValue(String key){
        if(Util.isEmpty(property)) return null;
        return property.getProperty(key);
    }
    
    public static List<String> getValues(String key){
        if(Util.isEmpty(property)) return null;
        String result = property.getProperty(key);
        if(Util.isEmpty(result)) return new ArrayList<String>();
        List<String> existsValue = findValues(key);
        if(!existsValue.isEmpty()) return existsValue;
        result = result.trim();
        Map<String, List<String>> value = new HashMap<String, List<String>>();
        existsValue = setValues(result);
        value.put(key, existsValue);
        values.offer(value);
        return existsValue;
        
    }
    
    private static List<String> findValues(String key){
        for(Map<String, List<String>> v : values){
            if(v.containsKey(key))
                return values.peek().get(key);
        }
        return new ArrayList<String>();
    }
    
    private static List<String> setValues(String names) {
        if(StringUtils.isEmpty(names))
            return new ArrayList<String>();
        names = names.trim();
        String[] limits = names.split(","); 
        
        return Arrays.asList(limits);
    }

    /**
     * @return the properties
     */
    public static Properties getProperties() {
        return property;
    }

    public static void setProperties(Properties properties){
        property = properties;
    }
    
    

}
