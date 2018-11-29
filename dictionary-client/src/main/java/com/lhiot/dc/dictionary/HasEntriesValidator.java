package com.lhiot.dc.dictionary;

import com.leon.microx.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Leon (234239150@qq.com) created in 15:07 18.11.28
 */
@Slf4j
public class HasEntriesValidator implements ConstraintValidator<HasEntries, Object> {

    private final DictionaryClient client;

    private String dictCode;

    private String message;

    @Autowired
    public HasEntriesValidator(DictionaryClient client) {
        log.info("HasEntries Validator initializing...");
        this.client = client;
    }

    @Override
    public void initialize(HasEntries annotation) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        this.dictCode = annotation.from();
        this.message = StringUtils.isBlank(annotation.message())
                ? "Dictionary validation failure!"
                : annotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(this.client)) {
            log.warn("DictionaryClient is not enabled, HasEntries Annotation is invalid!");
            return true;
        }
        if (value == null) {
            return true;
        }
        String[] values;
        if (ObjectUtils.isArray(value)){
            values = Arrays.stream((Object[])value).parallel().filter(Objects::nonNull).map(Object::toString).toArray(String[]::new);
        }else {
            values = new String[] {value.toString()};
        }
        boolean validated = this.client.of(this.dictCode).has(values);
        if (!validated) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
        return validated;
    }
}
