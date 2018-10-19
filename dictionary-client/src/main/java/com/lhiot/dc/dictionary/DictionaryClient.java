package com.lhiot.dc.dictionary;

import com.leon.microx.util.Pair;
import com.leon.microx.util.cache.SimpleCache;
import org.springframework.web.client.RestTemplate;

import java.util.Dictionary;
import java.util.concurrent.TimeUnit;

/**
 * @author Leon (234239150@qq.com) created in 11:47 18.10.16
 */
public class DictionaryClient {

    private HttpClient httpClient;

    private Pair<Long, TimeUnit> cacheTtl;

    private final SimpleCache<?, ?> localCache = new SimpleCache<>();

    private DictionaryClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static DictionaryClient server(RestTemplate restTemplate, String baseUrl) {
        return new DictionaryClient(new HttpClient(restTemplate).withRoot(baseUrl));
    }

    public DictionaryClient withLocalCacheTtl(Pair<Long, TimeUnit> cacheTtl) {
        this.cacheTtl = cacheTtl;
        return this;
    }

    public Dictionary dictionary(String code){

        return null;
    }
}
