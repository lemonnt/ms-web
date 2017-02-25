/**
 * @author : Gavin Li
 * @date   : Oct 29, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.lsf.core.LSFSession
 */
package com.lemonnt.ms.lsf.core;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.lemonnt.ms.common.bean.Encryption;
import com.lemonnt.ms.common.util.Util;

/**
 * @author : Gavin Li
 * @date   : Oct 29, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.lsf.core.LSFSession
 */
public abstract class LSFSession extends LSFRequest{
    private static Logger logger = Logger.getLogger(LSFSession.class);
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private  HttpSession session ;
    
    public HttpSession getSession(){
        HttpServletRequest request = getRequest();
        if(Util.isEmpty(request))
            return null;
        this.session = request.getSession(true);
        return this.session;
    }

    
    public Object getAttribute(String key){
        getSession();
        if(session.isNew()) return null;
        return session.getAttribute(key);
    }
    
    public void setAttribute(String key,String value){
        getSession();
        session.setAttribute(key, value);
    }
    
    public void remove(String key){
        getSession();
        session.removeAttribute(key);
        
    }
    
    public void invalidate(String userName){
        getSession();
        if(session.isNew()) return;
        if(Util.isEmpty(userName)) return;
        if(userName.equals(session.getAttribute("name"))){
            //session.removeAttribute("sid");
           // session.removeAttribute("name");
            session.invalidate();
        }else{
            logger.warn("userName ["+userName+"] was failed to invalidate ...");
        }
        
    }
    
    public void setAuthencationSid(String userName){
        getSession();
        session.setAttribute("name", userName);  
        session.setAttribute("sid", new Encryption().encryptionNormal(session.getId(),userName));
    }
    
    public void setLanguages(String language){
        getSession();
        session.setAttribute("language", language);
    }
    
    public boolean authencationSid(String sid){
        if(Util.isEmpty(sid)) return false;
        String encrySid = new Encryption().encryptionNormal(session.getId(),String.valueOf(getAttribute("name")));
        if(sid.equals(encrySid)) return true;
        if(sid.equals(session.getId())) return true;
        return false;
    }
    
    
    public String getSessionId(){
        getSession();
        return session.getId();
    }
}
