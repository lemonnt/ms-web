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
public enum EncryptionType {
    MD5("md5");
    private String name;
    
    
    EncryptionType(String name){
        this.name = name;
    }
    
    
    public static EncryptionType forName(String name) {
        if (name != null) {
            name = name.trim();
        }
        return EncryptionType.valueOf(name);
       
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

    
}
