package com.lhiot.dc.dictionary;

import com.leon.microx.util.Maps;
import com.leon.microx.util.Pair;
import com.leon.microx.util.cache.SimpleCache;
import com.leon.microx.web.http.RemoteInvoker;
import com.lhiot.dc.dictionary.module.Dictionary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author Leon (234239150@qq.com) created in 11:47 18.10.16
 */
public class DictionaryClient {

    private RemoteInvoker.Requester httpClient;

    private Pair<Long, TimeUnit> cacheTtl;

    private final SimpleCache<Object, Object> localCache = new SimpleCache<>();

    public DictionaryClient(String server, RemoteInvoker httpClient) {
        this.httpClient = httpClient.server(server);
    }

    private DictionaryClient withCacheTtl(Pair<Long, TimeUnit> cacheTtl) {
        this.cacheTtl = cacheTtl;
        return this;
    }

    public Dictionary dictionary(String code) {
        Object value = localCache.get(code);
        if (Objects.isNull(value)) {
            value = this.caching(code, () ->
                    this.httpClient.uriVariables(Maps.of("code", code)).queryParams(Maps.of("includeEntries", "true"))
                            .request("/dictionary/{code}", HttpMethod.GET, ParameterizedTypeReference.forType(Dictionary.class))
            );
        }
        return (Dictionary) value;
    }

    private <T> T caching(Object key, Supplier<ResponseEntity<T>> supplier) {
        T value = null;
        ResponseEntity<T> response = supplier.get();
        if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
            value = response.getBody();
            localCache.put(key, value, cacheTtl.getFirst(), cacheTtl.getSecond());
        }
        return value;
    }
}
