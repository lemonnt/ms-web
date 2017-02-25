/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.Account
 */
package com.lemonnt.ms.login.bean;

import java.util.Date;
/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.Account
 */
public class Account{
    private String name;
    private String password;
    private String email;
    private Date createdate;
    private String cellphone;
    //zh --->chinese  en---> english
    private String language = "zh";
    //100---> 超级管理员权限  1001 --- >管理员权限 1002 --- > 普通用户  提供管理页面进行权限管理
    private Integer permissionLevel;
    //0 ---> 未登录  1---->已经登录
    private Integer loginStatus;
    //0 --- > lock 1 --- > open
    private Integer isLock;
    
    private String sessionId;
    
    
    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }
    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    /**
     * @return the isLock
     */
    public Integer getIsLock() {
        return isLock;
    }
    /**
     * @param isLock the isLock to set
     */
    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }
    
    /**  
	 * language.  
	 *  
	 * @return  the language  
	 * @since   JDK 1.6  
	 */
	public String getLanguage() {
		return language;
	}
	/**  
	 * language.  
	 *  
	 * @param   language    the language to set  
	 * @since   JDK 1.6  
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
     * @return the permissionLevel
     */
    public Integer getPermissionLevel() {
        return permissionLevel;
    }
    /**
     * @param permissionLevel the permissionLevel to set
     */
    public void setPermissionLevel(Integer permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
    /**
     * @return the loginStatus
     */
    public Integer getLoginStatus() {
        return loginStatus;
    }
    /**
     * @param loginStatus the loginStatus to set
     */
    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
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
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the createdate
     */
    public Date getCreatedate() {
        return createdate;
    }
    /**
     * @param createdate the createdate to set
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    /**
     * @return the cellphone
     */
    public String getCellphone() {
        return cellphone;
    }
    /**
     * @param cellphone the cellphone to set
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    
    
    

}
