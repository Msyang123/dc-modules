package com.lhiot.dc.dictionary;

import org.springframework.web.client.RestTemplate;

/**
 * @author Leon (234239150@qq.com) created in 11:52 18.10.16
 */
class HttpClient {

    private String baseUrl;

    private RestTemplate restTemplate;

    HttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    HttpClient withRoot(String url){
        this.baseUrl = url;
        return this;
    }
}
