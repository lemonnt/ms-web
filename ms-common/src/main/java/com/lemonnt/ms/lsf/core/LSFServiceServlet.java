/**
 * @author : Gavin Li
 * @date   : Jun 13, 2016
 * @class  :com.cisco.webex.servlet.EagleServiceServlet
 */
package com.lemonnt.ms.lsf.core;

import java.io.*;
import java.lang.reflect.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.*;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lemonnt.ms.lsf.bean.RealMethodAndParameters;

import static com.lemonnt.ms.lsf.util.AnnotationUtil.*;

/**
 * @author : Gavin Li
 * @date   : Jun 13, 2016
 * @class  :com.cisco.webex.servlet.EagleServiceServlet
 */
public class LSFServiceServlet extends LSFSession {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(LSFServiceServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {       
        if(!authencation(req,resp)){
            String appName = req.getContextPath();
            resp.sendRedirect(appName+"/modules/login.jsp");
        }          
        resp.setContentType("text/html;charset=UTF-8");
        String[] json = req.getParameterValues("data");
        if (null != json) {
            ResponseResult result = new ResponseResult();
            WebExecuteLsf webExecuteLsf = new WebExecuteLsf();
            JSONObject params = JSONObject.parseObject(json[0]);
            try {
                String serviceName = getClassName(params);
                String methodName = getMethodName(params);
                Object bean = getBean(serviceName);
                if(StringUtils.isEmpty(serviceName) || StringUtils.isEmpty(methodName) || StringUtils.isEmpty(bean))
                    throw new IllegalServiceException("Failed to getService [],serviceName is null or methodName is null,or there is no related bean");
                JSONArray jsonArray = getJsonArray(params);
                RealMethodAndParameters realMethodAndParameters = getParamsForLowerJdk(bean, methodName);
                Type type = new Type();
                type.setClazzes(realMethodAndParameters.getParameters());               
                Method method = getMethod(bean, realMethodAndParameters);
                String moniterName = monitortime(bean,method);             
                if(null != moniterName){
                    result = new CallBack(moniterName).recordMonitorTime(bean, method, type, jsonArray, webExecuteLsf, result);
                }else{
                    Object object = webExecuteLsf.call(bean,method,type,jsonArray);
                    result.setData(object);
                    result.setError(false);
                    result.setException(null);
                    result.setExceptionMessage(null);
                }
            } catch (Throwable t) {
                result.setData(null);
                result.setError(true);
                if (t instanceof InvocationTargetException) {
                    InvocationTargetException ie = (InvocationTargetException) t;
                    if (ie.getTargetException() != null) {
                        t = ie.getTargetException();
                    }
                }
                result.setException(t);
                result.setExceptionMessage(t.getMessage());
                logger.error("Execute lsf service error", t);
            }
            resp.setHeader("Content-Type", "application/json;charset=UTF-8");
            resp.setContentType("application/json;charset=UTF-8");
            OutputStream outStream = null;
            try {
                outStream = resp.getOutputStream();
                String results = JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect);
                outStream.write(results.getBytes("UTF-8"));
            } finally {
                if (outStream != null) {
                    outStream.close();
                }
            }
            return;
        }
        
    }
    
    protected void doPosts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String[] json = req.getParameterValues("data");
       // String[] json= new String[]{createJson().toString()};
       // Object bean = EagleBeanStore.getBean("dailyReportSumService");
        if (null != json) {
            ResponseResult result = new ResponseResult();
            WebExecuteLsf webExecuteLsf = new WebExecuteLsf();
            try {    
                Object object = webExecuteLsf.call(json);
               // Object object = webExecuteLsf.call(bean,method,parameters,jsonArray);
                result.setData(object);
                result.setError(false);
                result.setException(null);
                result.setExceptionMessage(null);
            } catch (Throwable t) {
                result.setData(null);
                result.setError(true);
                if (t instanceof InvocationTargetException) {
                    InvocationTargetException ie = (InvocationTargetException) t;
                    if (ie.getTargetException() != null) {
                        t = ie.getTargetException();
                    }
                }
                result.setException(t);
                result.setExceptionMessage(t.toString());
                logger.error("Execute lsf service error", t);
            }
            resp.setHeader("Content-Type", "application/jason;charset=UTF-8");
            resp.setContentType("application/jason;charset=UTF-8");
            OutputStream outStream = null;
            try {
                outStream = resp.getOutputStream();
                String results = JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect);
                outStream.write(results.getBytes("UTF-8"));
            } finally {
                if (outStream != null) {
                    outStream.close();
                }
            }
            return;
        }
        
    }
    
    
    private String getMethodName(JSONObject params){
        return params.getString("methodName");
        
    }
    
    private String getClassName(JSONObject params){
        return params.getString("moduleName");
        
    }
    
    private Object getBean(String className){
        return LSFBeanStore.getBean(className);
        
    }
    
    private JSONArray getJsonArray(JSONObject params){
        Object obj = params.get("$data");
        if (obj != null && !(obj instanceof JSONArray)) {
            throw new RuntimeException("the data must be an array");
        }
        return params.getJSONArray("$data");
    }
    
    private Method getMethod(Object bean,RealMethodAndParameters realMethodAndParameters) throws NoSuchMethodException, SecurityException{
        return bean.getClass().getMethod(realMethodAndParameters.getRealName(), realMethodAndParameters.getParameters());
    }
    
//    @SuppressWarnings("unused")
//    private Class<?>[] getParams(Object bean,String methodName){
//        return new WebExecuteLsf().getMethodParametersType(bean, methodName);
//    }
    
 
    private RealMethodAndParameters getParamsForLowerJdk(Object bean,String methodName){
        return new WebExecuteLsf().getMethodParametersTypeforLowerJdk(bean, methodName);
    }
    
//    @SuppressWarnings("unused")
//    private Type getParameters(Object bean,String methodName){     
//        return  new WebExecuteLsf().getMethodParametersTypes(bean, methodName);
//    }
    
//    private JSONObject createJson(){
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("methodName", "getOpdbPstnSummation");
//        jsonObject.put("moduleName", "dailyReportController");
//        return jsonObject;
//    }
    
    
    public boolean authencation(HttpServletRequest request,HttpServletResponse response) throws IOException{       
         String sid = String.valueOf(getAttribute("sid"));
         sid = (sid == null || "null".equals(sid)) ? getSessionId() : sid;
         return authencationSid(sid); 
    }
    
}
