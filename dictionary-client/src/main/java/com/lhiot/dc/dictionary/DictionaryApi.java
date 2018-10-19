package com.lhiot.dc.dictionary;

import com.leon.microx.util.Pair;
import lombok.Getter;
import org.springframework.http.HttpMethod;

/**
 * @author Leon (234239150@qq.com) created in 14:31 18.10.18
 */
public enum DictionaryApi {

    GET_DICT("/", HttpMethod.GET);

    @Getter
    private Pair<String, HttpMethod> predefine;

    DictionaryApi(String uri, HttpMethod httpMethod) {
        this.predefine = Pair.of(uri, httpMethod);
    }
}
