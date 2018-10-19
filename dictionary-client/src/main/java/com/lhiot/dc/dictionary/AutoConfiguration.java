package com.lhiot.dc.dictionary;

import com.leon.microx.util.Maps;
import com.leon.microx.web.http.RemoteInvoker;
import com.leon.microx.web.http.RemoteProperties;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Leon (234239150@qq.com) created in 11:22 18.10.19
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "lhiot.dictionary.server")
public class AutoConfiguration {

    private String baseUrl;

    @Bean("dictionary-client")
    public RemoteInvoker httpClient(ObjectProvider<RestTemplate> restTemplate) {
        RemoteProperties properties = new RemoteProperties();
        properties.setServers(Maps.of("dictionary-service", new RemoteProperties.RemoteServer(this.baseUrl)));
        return RemoteInvoker.of(restTemplate.getIfAvailable(RestTemplate::new), properties);
    }

    @Bean
    public DictionaryClient dictionaryClient(@Qualifier("dictionary-client") RemoteInvoker httpClient) {
        return new DictionaryClient(this.baseUrl, httpClient);
    }
}
