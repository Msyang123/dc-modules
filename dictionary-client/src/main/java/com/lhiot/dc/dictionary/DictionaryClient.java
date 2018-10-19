package com.lhiot.dc.dictionary;

import com.leon.microx.util.Pair;
import com.leon.microx.util.cache.SimpleCache;
import com.leon.microx.web.http.RemoteInvoker;
import com.lhiot.dc.dictionary.module.Dictionary;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Leon (234239150@qq.com) created in 11:47 18.10.16
 */
public class DictionaryClient {

    private RemoteInvoker.Requester httpClient;

    private Pair<Long, TimeUnit> cacheTtl;

    private final SimpleCache<Object, Object> localCache = new SimpleCache<>();

    private DictionaryClient(String server, RemoteInvoker httpClient) {
        this.httpClient = httpClient.server(server);
    }

    private DictionaryClient withCacheTtl(Pair<Long, TimeUnit> cacheTtl) {
        this.cacheTtl = cacheTtl;
        return this;
    }

    public Dictionary dictionary(String code) {
        Object value = localCache.get(code);
        if (Objects.isNull(value)) {
            ResponseEntity<Dictionary> response = this.httpClient.request(DictionaryApi.GET_DICT.getPredefine(), Dictionary.class);
            if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
                value = response.getBody();
                localCache.put(code, value, cacheTtl.getFirst(), cacheTtl.getSecond());
            }
        }
        return (Dictionary) value;
    }
}
