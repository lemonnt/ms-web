/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.Constant
 */
package com.lemonnt.ms.common.bean;

/**
 * @author : Gavin Li
 * @date   : Oct 26, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.bean.Constant
 */
public interface Constant {
     static final int BUFFER = 20480;
     static final String DOT = ".";
     static final String LEFT_SLASH = "/";
     static final String RIGHT_SLASH = "\\";
     static final String DEFAULT_EMAIL_REGEXP = "^(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+$";
     static final String DEFAULT_NAME_REGEXP = "^[a-zA-Z0-9_]{3,24}$";
     static final String DEFAULT_PASSWORD_REGEXP = "^(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{8,32}$";
     static final String DEFAULT_CELLPHONYNUMBER_REGEXP = "^1[3|4|5|7|8]\\d{9}$";
     static final String DEFAULT_IP_REGEXP = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";

}
