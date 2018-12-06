package com.lhiot.dc.util;

import java.lang.annotation.*;

/**
 * @author xiaojian  created in  2018/12/5 15:07
 */
@Documented
@Retention(RetentionPolicy.RUNTIME) // 运行时注解
@Target({ElementType.TYPE, ElementType.METHOD}) // 注解类型， 级别
public @interface ReadArticle {
    String value() default "";
}
