package com.lhiot.dc.dictionary;

import com.leon.microx.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @author Leon (234239150@qq.com) created in 15:07 18.11.28
 */
@Slf4j
public class DictValidatorForString implements ConstraintValidator<DictValid, String> {

    @Autowired
    private DictionaryClient client;

    private String dictionaryCode;

    private String[] entryCodes;

    private String message;

    @Override
    public void initialize(DictValid annotation) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        this.dictionaryCode = annotation.code();
        this.entryCodes = annotation.entry();
        this.message = StringUtils.isBlank(annotation.message())
                ? "Dictionary validation failure!"
                : annotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(this.client)) {
            log.warn("DictionaryClient is not enabled, DictValid Annotation is invalid!");
            return true;
        }
        if (value == null) {
            return true;
        }
        boolean validated = this.client.of(this.dictionaryCode).has(entryCodes);
        if (!validated) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
        return validated;
    }
}
