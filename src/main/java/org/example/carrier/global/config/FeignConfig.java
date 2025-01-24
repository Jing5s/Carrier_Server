package org.example.carrier.global.config;

import org.example.carrier.global.interceptor.ContentLengthRequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "org.example.carrier.global.feign")
public class FeignConfig {
    @Bean
    public ContentLengthRequestInterceptor contentLengthRequestInterceptor() {
        return new ContentLengthRequestInterceptor();
    }
}
