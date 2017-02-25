/**
 * @author : Gavin Li
 * @date   : Jun 15, 2016
 * @class  :com.cisco.webex.daily.util.Page
 */
package com.lemonnt.ms.common.bean;

import java.lang.annotation.*;

/**
 * @author : Gavin Li
 * @date   : Jun 15, 2016
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Matcher {
    public String value() default "";
    public RegExpression expression() default RegExpression.NotNull;

}
