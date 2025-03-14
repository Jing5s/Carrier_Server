package org.example.carrier.global.utils;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NextcloudFeignConfiguration {

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor(@Value("${nextcloud.username}") String username,
                                                          @Value("${nextcloud.password}") String password) {
        return requestTemplate -> {
            String auth = username + ":" + password;
            byte[] encodedAuth = org.apache.commons.codec.binary.Base64.encodeBase64(auth.getBytes());
            String authHeader = "Basic " + new String(encodedAuth);
            requestTemplate.header("Authorization", authHeader);
        };
    }
}
