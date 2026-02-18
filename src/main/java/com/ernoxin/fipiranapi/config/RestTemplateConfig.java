package com.ernoxin.fipiranapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate fipiranRestTemplate(
            RestTemplateBuilder builder,
            @Value("${external.fipiran.base-url}") String baseUrl,
            @Value("${external.fipiran.user-agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)}") String userAgent,
            @Value("${external.fipiran.referer:https://www.fipiran.ir/}") String referer,
            @Value("${external.fipiran.origin:https://www.fipiran.ir}") String origin
    ) {
        return builder
                .rootUri(baseUrl)
                .defaultHeader(HttpHeaders.USER_AGENT, userAgent)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.ALL_VALUE)
                .defaultHeader(HttpHeaders.REFERER, referer)
                .defaultHeader(HttpHeaders.ORIGIN, origin)
                .build();
    }
}
