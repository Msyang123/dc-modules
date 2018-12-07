package com.lhiot.dc.util;

import com.lhiot.dc.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author xiaojian  created in  2018/12/5 15:15
 */
@Aspect
@Component
@Slf4j
public class ReadArticleAspect {
    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
    private ExpressionParser parser = new SpelExpressionParser();
    private ApplicationEventPublisher publisher;

    public ReadArticleAspect(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @AfterReturning(returning = "result", pointcut = "@annotation(readArticle)")
    public void afterReturn(JoinPoint point, ResponseEntity result, ReadArticle readArticle) {
        if (!ObjectUtils.isEmpty(readArticle.value())) {
            Boolean addReadAmount = "TRUE".equals(readArticle.value().toUpperCase()) ? Boolean.TRUE : this.getValue(point, readArticle.value());
            if (addReadAmount && result.getStatusCode().equals(HttpStatus.OK) && result.hasBody()) {
                if (result.getBody() instanceof Article) {
                    publisher.publishEvent(new ReadArticleEvent(((Article) result.getBody()).getId()));
                }
            }
        }
    }

    private Boolean getValue(JoinPoint point, String paramName) {
        String[] paramNames = discoverer.getParameterNames(((MethodSignature) point.getSignature()).getMethod());
        if (!ObjectUtils.isEmpty(paramNames)) {
            Object[] paramValues = point.getArgs();
            EvaluationContext context = new StandardEvaluationContext();
            if (paramValues.length > 0 && paramNames.length == paramValues.length) {
                for (int i = 0; i < paramNames.length; i++) {
                    context.setVariable(paramNames[i], paramValues[i]);
                }
                return parser.parseExpression(paramName).getValue(context, Boolean.class);
            }
        }
        return Boolean.FALSE;
    }

}
