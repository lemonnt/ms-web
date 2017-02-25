/**
 * @author : Gavin Li
 * @date   : Jun 17, 2016
 * @class  :com.cisco.webex.servlet.Type
 */
package com.lemonnt.ms.lsf.core;

/**
 * @author : Gavin Li
 * @date   : Jun 17, 2016
 * @class  :com.cisco.webex.servlet.Type
 */
public class Type {
    private Class<?> clazz;
    private Class<?> genericType;
    private Class<?>[] clazzes;
    private Type[] types;
    /**
     * @return the clazz
     */
    public Class<?> getClazz() {
        return clazz;
    }
    /**
     * @param clazz the clazz to set
     */
    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
    /**
     * @return the genericType
     */
    public Class<?> getGenericType() {
        return genericType;
    }
    /**
     * @param genericType the genericType to set
     */
    public void setGenericType(Class<?> genericType) {
        this.genericType = genericType;
    }
    /**
     * @return the clazzes
     */
    public Class<?>[] getClazzes() {
        return clazzes;
    }
    /**
     * @param clazzes the clazzes to set
     */
    public void setClazzes(Class<?>[] clazzes) {
        this.clazzes = clazzes;
    }
    /**
     * @return the types
     */
    public Type[] getTypes() {
        return types;
    }
    /**
     * @param types the types to set
     */
    public void setTypes(Type[] types) {
        this.types = types;
    }
    
    
    

}
