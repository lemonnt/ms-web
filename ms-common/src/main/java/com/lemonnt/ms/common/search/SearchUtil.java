/**
 * @author : Gavin Li
 * @date   : Nov 15, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.search.SearchUtil
 */
package com.lemonnt.ms.common.search;

import java.lang.reflect.Field;
import java.util.*;

import org.springframework.util.StringUtils;

import com.lemonnt.ms.common.util.Util;

/**
 * @author : Gavin Li
 * @date   : Nov 15, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.search.SearchUtil
 */
public class SearchUtil {
    public final transient static String _STRING = "java.lang.String";
    public final transient static String _NUMBER = "java.lang.Integer,java.lang.Double,java.lang.Float,int,double,float,java.lang.Long,long";
    public final transient static String _DATE = "java.util.Date";
    final transient static String _COLON = ":";
    /**
     * if the field add an annotation like 'Search' and the search's value is not null or default value,
     * it will return value as key,or return field name 
     * 
     */
    public static Map<String, String> searchField(Class<?> clazz){
        if (Util.isEmpty(clazz)) return new HashMap<String, String>();
        Map<String, String> result = new HashMap<String, String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Integer i = 0, length = fields.length - 1; i <= length; i++) {
            Field field = fields[i];
            Search search = field.getAnnotation(Search.class);
            if (null == search) continue;
            String value = search.value();
            if (null == value) continue;
            if ("".equals(value) || "()".equals(value)) {
                result.put(field.getName().toUpperCase(), field.getType().getName());
            } else {
                result.put(value.toUpperCase(), field.getType().getName());
            }


        }
        return result;
    }
    
    public static String search(String content,Class<?> clazz){
        return search(content, searchField(clazz));
    }
    
    private static String checkContent(String content,Map<String, String> result){
        String[] contents = content.split(_COLON);
        if(contents.length == 2){
            String key = contents[0].toUpperCase();
            if(result.containsKey(key)){
                String value= result.get(key);                
                result.clear();
                result.put(key, value);
                return contents[1];
            } 
        }
        return content;
        
    }
    public static String search(String content,Map<String, String> _TABLE_DEFAULT) {
        if (StringUtils.isEmpty(content) || null == _TABLE_DEFAULT || _TABLE_DEFAULT.isEmpty()) return null;
        if(content.length() >= 100) return null;
        content = checkContent(content, _TABLE_DEFAULT);
        String search = "(";
        for (Map.Entry<String, String> s : _TABLE_DEFAULT.entrySet()) {
            String key = s.getKey(), value = s.getValue();
            if (value.equals(_STRING)) {
                if(!search.equals("(")){
                    search += " or " + key + " like'%" + content + "%'";
                }else{
                    search +=key + " like '%" + content + "%'";
                }
                
            } else if (_NUMBER.contains(value) && Util.match(content)) {
                if(!search.equals("(")){
                    search += " or " + key + "=" + content;
                }else{
                    search +=key + "=" + content;
                }
                
            } else if (value.equals(_DATE) && Util.matchDate(content)) {
                if(!search.equals("(")){
                    search += " or " + key + "='" + content + "'";
                }else{
                    search +=key + "='" + content + "'";
                }
                
            }
        }
        search += ")";
        return search;
    }
}
