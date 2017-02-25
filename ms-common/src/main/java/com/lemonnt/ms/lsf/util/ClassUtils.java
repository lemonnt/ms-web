package com.lemonnt.ms.lsf.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ClassUtils {
	public static Class<?> forName(String[] packages, String className, ClassLoader classLoader) {
		try {
			return _forName(className, classLoader);
		} catch (ClassNotFoundException e) {
			if (packages != null && packages.length > 0) {
				for (String pkg : packages) {
					try {
						return _forName(pkg + "." + className, classLoader);
					} catch (ClassNotFoundException e2) {
					}
				}
			}
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	public static Class<?> _forName(String className, ClassLoader classLoader) throws ClassNotFoundException {
		if ("boolean".equals(className))
			return boolean.class;
		if ("byte".equals(className))
			return byte.class;
		if ("char".equals(className))
			return char.class;
		if ("short".equals(className))
			return short.class;
		if ("int".equals(className))
			return int.class;
		if ("long".equals(className))
			return long.class;
		if ("float".equals(className))
			return float.class;
		if ("double".equals(className))
			return double.class;
		if ("boolean[]".equals(className))
			return boolean[].class;
		if ("byte[]".equals(className))
			return byte[].class;
		if ("char[]".equals(className))
			return char[].class;
		if ("short[]".equals(className))
			return short[].class;
		if ("int[]".equals(className))
			return int[].class;
		if ("long[]".equals(className))
			return long[].class;
		if ("float[]".equals(className))
			return float[].class;
		if ("double[]".equals(className))
			return double[].class;
		try {
			return arrayForName(className, classLoader);// 去查询array获取原始类型
		} catch (ClassNotFoundException e) {
			if (className.indexOf('.') == -1) { // 尝试java.lang包
				try {
					return arrayForName("java.lang." + className, classLoader);
				} catch (ClassNotFoundException e2) {
					//FIXME
				}
			}
			throw e;
		}
	}

	private static Class<?> arrayForName(String className, ClassLoader classLoader) throws ClassNotFoundException {
		return Class.forName(className.endsWith("[]") ? "[L" + className.substring(0, className.length() - 2) + ";" : className, true, classLoader);// 如果className是以[]结尾表示是一个数组，则使用[L开始，否则直接使用class的名称
	}

	public static String toString(Throwable e) {
		StringWriter w = new StringWriter();
		PrintWriter p = new PrintWriter(w);
		p.print(e.getClass().getName() + ": ");
		if (e.getMessage() != null) {
			p.print(e.getMessage() + "\n");
		}
		p.println();
		try {
			e.printStackTrace(p);
			return w.toString();
		} finally {
			p.close();
		}
	}

	public static String getName(Class<?> c) {
		if (c.isArray()) {
			StringBuilder sb = new StringBuilder();
			do {
				sb.append("[]");
				c = c.getComponentType();
			} while (c.isArray());

			return c.getName() + sb.toString();
		}
		return c.getName();
	}

	@SuppressWarnings("rawtypes")
	/**
	 * @param clz
	 * @return
	 */
	public static boolean isWrapper(Class<?> clz) {
		try {
			return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}

	public static Object toWrapperInstance(Object obj, Class<?> cls) {
		if (cls == int.class || cls == Integer.class) {
			return Integer.valueOf(obj.toString());
		} else if (cls == double.class || cls == Double.class) {
			return Double.valueOf(obj.toString());
		} else if (cls == float.class || cls == Float.class) {
			return Float.valueOf(obj.toString());
		} else if (cls == short.class || cls == Short.class) {
			return Short.valueOf(obj.toString());
		} else if (cls == char.class || cls == Character.class) {
			return (Character) obj;
		} else if (cls == byte.class || cls == Byte.class) {
			return (Byte) obj;
		} else if (cls == boolean.class || cls == Boolean.class) {
			return Boolean.valueOf(obj.toString());
		} else if (cls == long.class || cls == Long.class) {
			return Long.valueOf(obj.toString());
		} else {
			return obj;
		}
	}
	
	public static boolean isEmpty(Object obj){
	    return (null == obj || "".equals(obj));
	}
	
	public static String parseSpringModelView(Class<?> clazz){
	    return "";
	    
	}
	
	public static Class<?>[] getParameterizedType(Field f) {
		Type fc = f.getGenericType();
		if (fc != null && fc instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) fc;
			Type[] types = pt.getActualTypeArguments();
			if (types != null && types.length > 0) {
				Class<?>[] classes = new Class<?>[types.length];
				for (int i = 0,length = classes.length; i < length; i++) {
					classes[i] = (Class<?>) types[i];
				}
				return classes;
			}
		}
		return null;
	}
	
	//获取泛型类型
	public static Class<?> getGenericClass(Field field) {
	    Class<?> fieldClazz = field.getType();
	 
	    if (fieldClazz.isAssignableFrom(List.class)) {
	        Type fc = field.getGenericType();
	        if (fc instanceof ParameterizedType)
	        {
	            ParameterizedType pt = (ParameterizedType) fc;
	 
	            fieldClazz = (Class<?>) pt.getActualTypeArguments()[0];
	        }
	    } 
	    return fieldClazz;
	}

}
