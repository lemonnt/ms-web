/**
 * @author : Gavin Li
 * @date   : Oct 29, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.bean.AccountStatus
 */
package com.lemonnt.ms.login.bean;


/**
 * @author : Gavin Li
 * @date   : Oct 29, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.bean.AccountStatus
 */
public enum AccountStatus {
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    DUP("DUP"),
    AUTHEXCEPTION("AUTHEXCEPTION"),
    OTHER("OTHER");
    
    private String name;
    
    
    AccountStatus(String name){
        this.name = name;
    }
    
    
    public static AccountStatus forName(String name) {
        if (name != null) {
            name = name.trim();
        }
        return AccountStatus.valueOf(name);
       
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
