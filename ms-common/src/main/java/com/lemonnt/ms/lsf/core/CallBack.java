package com.lemonnt.ms.lsf.core;

import java.lang.reflect.*;
import java.util.*;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONArray;

public class CallBack {
    private String moduleName;

    public CallBack() {

    }

    public CallBack(String moduleName) {
        this.moduleName = moduleName;
    }

    private static Logger logger = Logger.getLogger(CallBack.class);

    public void record(ICallBack iCallBack) {
        String moduleNameStr = null == moduleName ? "data" : moduleName;
        long startTime = System.currentTimeMillis();
        logger.info("Start to load the [" + moduleNameStr + "] , params = {}");
        iCallBack.recordProcedureRunTime();
        logger.info("Finish to load [" + moduleNameStr + "] ,the time was used ["
                + (System.currentTimeMillis() - startTime) + "] ms");
    }

    public Object recordMonitorTime(Method method,Object bean,List<Object> parametersList) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String moduleNameStr = null == moduleName ? "data" : moduleName;
        long startTime = System.currentTimeMillis();
        logger.info("Start to execute [" + moduleNameStr + "] , params = "+parametersList.toString());
        Object object = method.invoke(bean, parametersList.toArray(new Object[0]));
        logger.info("Finish to execute [" + moduleNameStr + "] ,the time was used ["
                + (System.currentTimeMillis() - startTime) + "] ms");
        return object;
    }
    
    public ResponseResult recordMonitorTime(Object bean,Method method,Class<?>[] parameters,JSONArray jsonArray,WebExecuteLsf webExecuteLsf,ResponseResult result) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String moduleNameStr = null == moduleName ? "data" : moduleName;
        long startTime = System.currentTimeMillis();
        logger.info("Start to execute [" + moduleNameStr + "] , params = "+Arrays.toString(parameters));
        Object object = webExecuteLsf.call(bean,method,parameters,jsonArray);
        Long time = System.currentTimeMillis() - startTime;
        result.setData(object);
        result.setError(false);
        result.setException(null);
        result.setExceptionMessage(null);
        result.setTime(time);
        logger.info("Finish to execute [" + moduleNameStr + "] ,the time was used ["
                + time + "] ms");
        return result;
    }
    
    public ResponseResult recordMonitorTime(Object bean,Method method,Type type,JSONArray jsonArray,WebExecuteLsf webExecuteLsf,ResponseResult result) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String moduleNameStr = null == moduleName ? "data" : moduleName;
        long startTime = System.currentTimeMillis();
        logger.info("Start to execute [" + moduleNameStr + "] , params = "+Arrays.toString(type.getClazzes()));
        Object object = webExecuteLsf.call(bean,method,type,jsonArray);
        Long time = System.currentTimeMillis() - startTime;
        result.setData(object);
        result.setError(false);
        result.setException(null);
        result.setExceptionMessage(null);
        result.setTime(time);
        logger.info("Finish to execute [" + moduleNameStr + "] ,the time was used ["
                + time + "] ms");
        return result;
    }

}
