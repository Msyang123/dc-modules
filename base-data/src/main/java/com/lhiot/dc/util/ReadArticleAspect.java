package com.lhiot.dc.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author xiaojian  created in  2018/12/5 15:15
 */
@Aspect
@Component
@Slf4j
public class ReadArticleAspect {
    private ApplicationEventPublisher publisher;

    public ReadArticleAspect(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Pointcut("@annotation(com.lhiot.dc.util.ReadArticle)")
    public void pointCut() {
    }

    @AfterReturning(returning = "response", pointcut = "pointCut()")
    public void afterReturn(JoinPoint joinPoint, Object response) {
        publisher.publishEvent(new ReadArticleEvent(joinPoint.getArgs(),(ResponseEntity) response));
    }

}
