/**
 * @author : Gavin Li
 * @date   : Oct 29, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.lsf.core.LSFRequest
 */
package com.lemonnt.ms.lsf.core;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author : Gavin Li
 * @date   : Oct 29, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.lsf.core.LSFRequest
 */
@SuppressWarnings("serial")
public abstract class LSFRequest extends HttpServlet{
    
    private  HttpServletRequest request ;
    
    public HttpServletRequest getRequest(){
        this.request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return this.request;
    }

}
