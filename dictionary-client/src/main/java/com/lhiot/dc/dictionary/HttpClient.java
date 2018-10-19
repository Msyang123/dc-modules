package com.lhiot.dc.dictionary;

import com.leon.microx.support.http.RemoteInvoker;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Leon (234239150@qq.com) created in 11:52 18.10.16
 */
class HttpClient {

    private RemoteInvoker invoker;

    HttpClient(RemoteInvoker invoker) {
        this.invoker = invoker;
    }

    HttpClient withRoot(String url) {
        this.headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        this.headers.setAccept(Arrays.asList(MediaType.ALL, MediaType.APPLICATION_JSON_UTF8));
        if (Objects.nonNull(remoteServer.getHttpHeaders())) {
            remoteServer.getHttpHeaders().forEach(this.headers::add);
        }
        this.baseUrl = url;
        return this;
    }

    public <T> ResponseEntity<T> get(String uri,) {

    }
}
