/**
 * @author : Gavin Li
 * @date   : Jun 15, 2016
 * @class  :com.cisco.webex.daily.util.Page
 */
package com.lemonnt.ms.common.search;

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
public @interface Search {
    public String value() default "";

}
