package com.lhiot.dc.dictionary;

import com.leon.microx.util.Maps;
import com.leon.microx.util.Pair;
import com.leon.microx.util.cache.SimpleCache;
import com.leon.microx.web.http.RemoteInvoker;
import com.leon.microx.web.result.Tips;
import com.lhiot.dc.dictionary.module.Dictionary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Leon (234239150@qq.com) created in 11:47 18.10.16
 */
@Slf4j
public class DictionaryClient {

    static final long DEFAULT_LOCAL_CACHE_TTL = 10_000L;

    private RemoteInvoker.Requester httpClient;

    private Pair<Long, TimeUnit> cacheTtl = Pair.of(DEFAULT_LOCAL_CACHE_TTL, TimeUnit.MILLISECONDS);

    private final SimpleCache<String, Dictionary> localCache = new SimpleCache<>();

    private static final Pair<String, HttpMethod> DICTIONARY_API = Pair.of("/dictionaries/{code}", HttpMethod.GET);

    DictionaryClient(String server, RemoteInvoker httpClient) {
        this.httpClient = httpClient.server(server);
    }

    void withCacheTtl(Pair<Long, TimeUnit> cacheTtl) {
        this.cacheTtl = cacheTtl;
    }

    public Tips<Dictionary> dictTips(String code){
        Optional<Dictionary> optional = this.dictionary(code);
        if (optional.isPresent()){
            return Tips.<Dictionary>info("exist").data(optional.get());
        }
        return Tips.warn("not found");
    }

    public Optional<Dictionary> dictionary(String code){
        return this.dictionary(code, true);
    }

    public Optional<Dictionary> dictionary(String code, boolean includeEntries) {
        Dictionary value = localCache.get(code);
        if (Objects.isNull(value)) {
            RemoteInvoker.Requester client = this.httpClient.uriVariables(Maps.of("code", code));
            if (includeEntries){
                client.queryParams(Maps.of("includeEntries", "true"));
            }
            try {
                ResponseEntity<Dictionary> response = client.request(DICTIONARY_API, Dictionary.class);
                if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
                    value = response.getBody();
                    localCache.put(code, value, cacheTtl.getFirst(), cacheTtl.getSecond());
                }
            }catch (Exception e){
                log.error(e.getMessage(), e);
            }
        }
        return Optional.ofNullable(value);
    }
}
