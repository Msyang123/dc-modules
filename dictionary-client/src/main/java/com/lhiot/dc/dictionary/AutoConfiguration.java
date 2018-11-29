package com.lhiot.dc.dictionary;

import com.leon.microx.util.Maps;
import com.leon.microx.util.Pair;
import com.leon.microx.web.http.RemoteInvoker;
import com.leon.microx.web.http.RemoteProperties;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static com.lhiot.dc.dictionary.DictionaryClient.DEFAULT_LOCAL_CACHE_TTL;

/**
 * @author Leon (234239150@qq.com) created in 11:22 18.10.19
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "lhiot.dictionary.server")
public class AutoConfiguration {

    private static final String CONFIG_KEY = "dictionary-service";
    private String baseUrl;
    private Long cacheSeconds = DEFAULT_LOCAL_CACHE_TTL;

    public AutoConfiguration(ObjectProvider<RestTemplate> provider) {
        this.restTemplate = provider.getIfAvailable(RestTemplate::new);
    }

    private final RestTemplate restTemplate;

    @Bean
    public Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean("dictionaryClientRemoteInvoker")
    public RemoteInvoker httpClient() {
        RemoteProperties properties = new RemoteProperties();
        properties.setServers(Maps.of(CONFIG_KEY, new RemoteProperties.RemoteServer(this.baseUrl)));
        return RemoteInvoker.of(this.restTemplate, properties);
    }

    @Bean
    public DictionaryClient dictionaryClient(@Qualifier("dictionaryClientRemoteInvoker") RemoteInvoker remoteInvoker) {
        DictionaryClient client = new DictionaryClient(CONFIG_KEY, remoteInvoker);
        client.withCacheTtl(Pair.of(this.cacheSeconds, TimeUnit.SECONDS));
        return client;
    }
}
