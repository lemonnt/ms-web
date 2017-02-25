/**
 * @author : Gavin Li
 * @date   : Jun 15, 2016
 * @class  :com.cisco.webex.daily.util.Page
 */
package com.lemonnt.ms.common.file.csv;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Gavin Li
 * @date   : Jun 15, 2016
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CSVField {
    public String value() default "";
    //start from number 1
    public int sort() default 0;

}
