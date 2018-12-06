package com.lhiot.dc.util;


import lombok.Data;
import org.springframework.http.ResponseEntity;

/**
 * @author xiaojian  created in  2018/12/6 8:52
 */
@Data
public class ReadArticleEvent {
    private Object[] requestArgs;
    private ResponseEntity responseEntity;

    public ReadArticleEvent(Object[] requestArgs, ResponseEntity responseEntity) {
        this.requestArgs = requestArgs;
        this.responseEntity = responseEntity;
    }
}
