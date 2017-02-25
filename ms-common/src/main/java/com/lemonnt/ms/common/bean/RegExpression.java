/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.RegExpression
 */
package com.lemonnt.ms.common.bean;


/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.RegExpression
 */
public enum RegExpression {
    EMAIL("^(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+$"),
    ACCOUNT_NAME("^[a-zA-Z0-9_]{3,24}$"),
    PASSWORD("^(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{8,32}$"),
    TELEPHONY_NUMBER("^1[3|4|5|7|8]\\d{9}$"),
    IP("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})"),
    NotNull("^\\S+(\\s+\\S+)*$"),
    NUMBER("(-?\\d+)"),
    DOUBLE("(-?\\d+)(\\.\\d+)?");
    private String name;
    
    
    RegExpression(String name){
        this.name = name;
    }
    
    
    public static RegExpression forName(String name) {
        if (name != null) {
            name = name.trim();
        }
        return RegExpression.valueOf(name);
       
    }
    
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        System.out.println(RegExpression.EMAIL.getName());
    }
    
    
}
