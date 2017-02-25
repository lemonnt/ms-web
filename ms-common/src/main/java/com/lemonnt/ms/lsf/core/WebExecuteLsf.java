/**
 * @author : Gavin Li
 * @date : Jun 14, 2016
 * @class :com.cisco.webex.servlet.WebExecuteLsf
 */
package com.lemonnt.ms.lsf.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.*;
import com.lemonnt.ms.lsf.bean.MethodName;
import com.lemonnt.ms.lsf.bean.RealMethodAndParameters;
import static com.lemonnt.ms.lsf.util.AnnotationUtil.*;
import static com.lemonnt.ms.lsf.util.ClassUtils.*;

/**
 * @author : Gavin Li
 * @date : Jun 14, 2016
 * @class :com.cisco.webex.servlet.WebExecuteLsf
 */
public final class WebExecuteLsf {
    private final static String LIST_TYPE = "java.util.list";

    protected Object call(String[] json) {
        if (null != json) {
            JSONObject params = JSONObject.parseObject(json[0]);
            String methodName = params.getString("methodName");
            String serviceName = params.getString("moduleName");
            Object obj = params.get("$data");
            if (obj != null && !(obj instanceof JSONArray)) {
                throw new RuntimeException("the data must be an array");
            }
            JSONArray jsonArray = params.getJSONArray("$data");
            Object object = callService(serviceName, methodName, jsonArray);
            return object;
        }
        return new Object();

    }

    protected Object call(Object bean, Method method, Class<?>[] parameters, JSONArray jsonArray) {
        return callService(bean, method, parameters, jsonArray);
    }

    protected Object call(Object bean, Method method, Type parameters, JSONArray jsonArray) {
        return callService(bean, method, parameters, jsonArray);
    }

    protected Object callService(String serviceName, String methodName, JSONArray jsonArray) {
        try {
            Object bean = LSFBeanStore.getBean(serviceName);
            Class<?>[] parameters = getMethodParametersTypeforLowerJdk(bean, methodName).getParameters();
            int jsonLength = 0;
            if (null != jsonArray) jsonLength = jsonArray.size();
            Method method = bean.getClass().getMethod(methodName, parameters);
            List<Object> parametersList = new ArrayList<Object>();
            if (parameters.length != 0) {
                for (int i = 0; i < parameters.length; i++) {
                    if (i < jsonLength) {
                        Class<?> cls = parameters[i];
                        Object obj = jsonArray.get(i);
                        if (cls.isAssignableFrom(obj.getClass())) {
                            parametersList.add(obj);
                        } else if (obj instanceof JSONObject) {
                            Object javaBean = JSONObject.toJavaObject(((JSON) obj), cls);
                            parametersList.add(javaBean);
                        } else if (obj instanceof JSONArray) {
                            Object collection = JSONArray.toJavaObject(((JSON) obj), cls);
                            parametersList.add(collection);
                        } else if (cls == String.class) {
                            parametersList.add(obj.toString());
                        } else if (cls.isPrimitive() || isWrapper(cls)) {
                            Object wrapperInstance = toWrapperInstance(obj, cls);
                            parametersList.add(wrapperInstance);
                        } else {
                            if (cls instanceof Object) {
                                Object object =
                                        JSONObject.toJavaObject(
                                                JSONObject.parseObject(obj.toString()),
                                                parameters[i]);
                                obj = object;
                            }
                            parametersList.add(obj);
                        }
                    } else {
                        parametersList.add(null);
                    }
                }
            }
            Object object = null;
            String moniterName = monitortime(bean, method);

            if (null == moniterName) {
                object = method.invoke(bean, parametersList.toArray(new Object[0]));
            } else {
                object = new CallBack(moniterName).recordMonitorTime(method, bean, parametersList);
            }
            // Object object = method.invoke(bean, parametersList.toArray(new Object[0]));
            return object;
        } catch (Exception e) {
            throw new IllegalReflectionException("Failed to callService [] ", e);
        }
    }

    protected Object callService(Object bean, Method method, Class<?>[] parameters,
            JSONArray jsonArray) {
        try {
            int jsonLength = 0;
            if (null != jsonArray) jsonLength = jsonArray.size();
            List<Object> parametersList = new ArrayList<Object>();
            if (parameters.length != 0) {
                for (int i = 0; i < parameters.length; i++) {
                    if (i < jsonLength) {
                        Class<?> cls = parameters[i];
                        Object obj = jsonArray.get(i);
                        if (cls.isAssignableFrom(obj.getClass())) {
                            parametersList.add(obj);
                        } else if (obj instanceof JSONObject) {
                            Object javaBean = JSONObject.toJavaObject(((JSON) obj), cls);
                            parametersList.add(javaBean);
                        } else if (obj instanceof JSONArray) {
                            Object collection = JSONArray.toJavaObject(((JSON) obj), cls);
                            parametersList.add(collection);
                        } else if (cls == String.class) {
                            parametersList.add(obj.toString());
                        } else if (cls.isPrimitive() || isWrapper(cls)) {
                            Object wrapperInstance = toWrapperInstance(obj, cls);
                            parametersList.add(wrapperInstance);
                        } else {
                            if (cls instanceof Object) {
                                Object object =
                                        JSONObject.toJavaObject(
                                                JSONObject.parseObject(obj.toString()), cls);
                                obj = object;

                            }
                            parametersList.add(obj);
                        }
                    } else {
                        parametersList.add(null);
                    }
                }
            }
            Object object = method.invoke(bean, parametersList.toArray(new Object[0]));
            return object;
        } catch (Exception e) {
            throw new IllegalReflectionException("Failed to callService [] ", e);
        }
    }

    protected Object callService(Object bean, Method method, Type type, JSONArray jsonArray) {
        try {
            int jsonLength = 0;
            if (null != jsonArray) jsonLength = jsonArray.size();
            List<Object> parametersList = new ArrayList<Object>();
            Class<?>[] parameters = type.getClazzes();
            Type[] types = type.getTypes();
            if (parameters.length != 0) {
                for (int i = 0; i < parameters.length; i++) {
                    if (i < jsonLength) {
                        Class<?> cls = parameters[i];
                        Object obj = jsonArray.get(i);
                        if (cls.isAssignableFrom(obj.getClass())) {
                            parametersList.add(obj);
                        } else if (obj instanceof JSONObject) {
                            Object javaBean = JSONObject.toJavaObject(((JSON) obj), cls);
                            parametersList.add(javaBean);
                        } else if (obj instanceof JSONArray) {
                            Object collection = JSONArray.toJavaObject(((JSON) obj), cls);
                            parametersList.add(collection);
                        } else if (cls == String.class) {
                            parametersList.add(obj.toString());
                        } else if (cls.isPrimitive() || isWrapper(cls)) {
                            Object wrapperInstance = toWrapperInstance(obj, cls);
                            parametersList.add(wrapperInstance);
                        } else {
                            if (cls instanceof Object) {
                                if (LIST_TYPE.equals(cls.getName().toLowerCase())) {
                                    List<?> object =
                                            JSONObject.parseArray(obj.toString(),
                                                    types[i].getGenericType());
                                    obj = object;
                                } else {
                                    Object object =
                                            JSONObject.toJavaObject(
                                                    JSONObject.parseObject(obj.toString()), cls);
                                    obj = object;
                                }

                            }
                            parametersList.add(obj);
                        }
                    } else {
                        parametersList.add(null);
                    }
                }
            }
            Object object = method.invoke(bean, parametersList.toArray(new Object[0]));
            return object;
        } catch (IllegalArgumentException e) {
            throw new IllegalReflectionException("Error argument", e);
        } catch (IllegalAccessException e) {
            throw new IllegalReflectionException("Illegal Access Exception", e);
        } catch (InvocationTargetException e) {
            throw new IllegalReflectionException(e.getCause().getMessage(), e);
        } catch (Exception e) {
            throw new IllegalReflectionException("Failed to callService,unknow reason", e);
        } 
    }

//    protected Class<?>[] getMethodParametersType(Object bean, String methodName) {
//        if (isEmpty(bean)) {
//            throw new IllegalServiceException("Invalid service [" + bean + "]");
//        }
//        Class<?>[] clazzss = null;
//        if (isEmpty(methodName))
//            throw new IllegalMethodException("[" + methodName + "] is Null,Please check it !");
//        Class<?> clazz = bean.getClass();
//        boolean isMethodFlag = true;
//        Method[] methods = clazz.getDeclaredMethods();
//        for (Method method : methods) {
//            String mname = method.getName();
//            if (methodName.equals(mname)) {
//                isMethodFlag = false;
//                Parameter[] parameters = method.getParameters();
//                clazzss = new Class<?>[parameters.length];
//                for (int i = 0, length = parameters.length; i <= length - 1; i++) {
//                    Class<?> parameterType = parameters[i].getType();
//                    clazzss[i] = parameterType;
//                }
//                break;
//            }
//        }
//
//        if (isMethodFlag) {
//            throw new IllegalMethodException("Invalid method [" + methodName + "]");
//        }
//        return clazzss;
//
//    }

//    protected Type getMethodParametersTypes(Object bean, String methodName) {
//        if (isEmpty(bean)) {
//            throw new IllegalServiceException("Invalid service [" + bean + "]");
//        }
//        Type result = new Type();
//        Type[] types = null;
//        Class<?>[] clazzes = null;
//        // Type[] clazzss = null;
//        if (isEmpty(methodName))
//            throw new IllegalMethodException("[" + methodName + "] is Null,Please check it !");
//        Class<?> clazz = bean.getClass();
//        boolean isMethodFlag = true;
//        Method[] methods = clazz.getDeclaredMethods();
//        for (Method method : methods) {
//            String mname = method.getName();
//            if (methodName.equals(mname)) {
//                isMethodFlag = false;
//                Parameter[] parameters = method.getParameters();
//                types = new Type[parameters.length];
//                clazzes = new Class<?>[parameters.length];
//                for (int i = 0, length = parameters.length; i <= length - 1; i++) {
//                    Type type = new Type();
//                    Class<?> parameterType = parameters[i].getType();
//                    type.setClazz(parameterType);
//                    clazzes[i] = parameterType;
//                    if (parameterType.getName().equals("java.util.List")) {
//                        ParameterizedType parameterizedType =
//                                (ParameterizedType) parameters[i].getParameterizedType();
//                        try {
//                            type.setGenericType(Class.forName(parameterizedType
//                                    .getActualTypeArguments()[0].getTypeName()));
//                        } catch (ClassNotFoundException e) {
//                            throw new IllegalReflectionException("Error parameters []");
//                        }
//                    }
//                    types[i] = type;
//                }
//                break;
//            }
//        }
//
//        if (isMethodFlag) {
//            throw new IllegalMethodException("Invalid method [" + methodName + "]");
//        }
//        result.setTypes(types);
//        result.setClazzes(clazzes);
//        return result;
//
//    }
//    
    
    public RealMethodAndParameters getMethodParametersTypeforLowerJdk(Object bean,String methodName){
        if(isEmpty(bean)){
            throw new IllegalServiceException("Invalid service ["+bean+"]");
        }
        Class<?>[] clazzss = null;
        if(isEmpty(methodName)) throw new IllegalMethodException("["+methodName+"] is Null,Please check it !");
        Class<?> clazz = bean.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        RealMethodAndParameters realMethodAndParameters = new RealMethodAndParameters();
        realMethodAndParameters.setDefinedName(methodName);
        for(Method method : methods){
        	MethodName annotationMethodName = method.getAnnotation(MethodName.class);
        	if(null == annotationMethodName){
        		String mname = method.getName();
                if(methodName.equals(mname)){
                    clazzss = method.getParameterTypes();
                    realMethodAndParameters.setAnnoationMethodName(methodName);
                    realMethodAndParameters.setRealName(methodName);
                    realMethodAndParameters.setParameters(clazzss);
                    break;
                }
        	}else{
        		String annotationName = annotationMethodName.value();
        		if(methodName.equals(annotationName)){
        			 clazzss = method.getParameterTypes();
        			 realMethodAndParameters.setAnnoationMethodName(annotationName);
                     realMethodAndParameters.setRealName(method.getName());
                     realMethodAndParameters.setParameters(clazzss);
                     break;
        		}
        	}
            
        }
        
        if(null == realMethodAndParameters.getRealName() || "".equals(realMethodAndParameters.getRealName())){
            throw new IllegalMethodException("Invalid method ["+methodName+"]");
        }
        
        return realMethodAndParameters;

    }


}
