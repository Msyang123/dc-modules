package com.lhiot.dc.dictionary;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 字典项验证注解
 * @author Leon (234239150@qq.com) created in 14:56 18.11.28
 */
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = HasEntriesValidator.class)
@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER, TYPE_USE})
public @interface HasEntries {

    /**
     * 哪个字典
     * @return dictionaryCode
     */
    String from();

    /**
     * 验证失败提示
     * @return string
     */
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
