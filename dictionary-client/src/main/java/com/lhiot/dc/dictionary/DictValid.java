package com.lhiot.dc.dictionary;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Leon (234239150@qq.com) created in 14:56 18.11.28
 */
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = DictValidatorForString.class)
@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER, TYPE_USE})
public @interface DictValid {

    String code();

    String[] entry();

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
