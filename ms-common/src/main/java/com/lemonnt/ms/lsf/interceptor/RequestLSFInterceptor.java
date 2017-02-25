/**
 * @author : Gavin Li
 * @date : Jun 13, 2016
 * @class :com.cisco.webex.interceptor.EagleRequestLSFInterceptor
 */
package com.lemonnt.ms.lsf.interceptor;

import static com.lemonnt.ms.lsf.util.AnnotationUtil.*;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lemonnt.ms.common.bean.AdvancedFile;
import com.lemonnt.ms.common.util.Util;
import com.lemonnt.ms.lsf.bean.Configuration;
import com.lemonnt.ms.lsf.core.LSFSession;
import com.lemonnt.ms.lsf.language.InternationalLanguage;

/**
 * @author : Gavin Li
 * @date : Jun 13, 2016
 * @class :com.cisco.webex.interceptor.EagleRequestLSFInterceptor
 */
public class RequestLSFInterceptor extends LSFSession implements HandlerInterceptor {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        List<String> configurations = Configuration.getValues("filter.pages");
        String urlPath = request.getServletPath();
        AdvancedFile advancedFile = new AdvancedFile();
        String urlName = advancedFile.name(urlPath);
        if(configurations.contains(urlName)) return true;
        boolean flag = authencationSid(String.valueOf(getAttribute("sid")));  
        if(!flag){
            String appName = request.getContextPath();
            response.sendRedirect(appName+"/modules/login.jsp");
            return false;
        }    
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
        
        if(handler instanceof HandlerMethod && !Util.isEmpty(modelAndView)){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            String page = page(handlerMethod.getMethod());
            String initViewName = modelAndView.getViewName(); 
            Method method = handlerMethod.getMethod();
            Class<?> clazz = handlerMethod.getBeanType(); 
            if(!isResponseBody(method))
                return;
            if(null == initViewName)
                initViewName = requestMapping(clazz)+"/"+requestMapping(method);
            if(!StringUtils.isEmpty(page)){
                initViewName = modelAndView.getViewName()+"/"+page;            
            }else{
                if("".equals(page)){
                    String clazzName = clazz.getSimpleName().toLowerCase();
                    clazzName = clazzName.contains("controller") ? clazzName.replaceAll("controller", ""):clazzName;
                    page = clazzName;
                    initViewName = modelAndView.getViewName()+"/"+clazzName;
                }
                
            }
            initViewName = initViewName.replaceAll("//", "/");          
            modelAndView.addObject("language", InternationalLanguage.getLanguage(String.valueOf(getAttribute("language")), page));
            modelAndView.setViewName(initViewName);
            
        }

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        // TODO Auto-generated method stub

    }

}
