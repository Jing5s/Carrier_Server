package org.example.carrier.global.config;

import feign.codec.ErrorDecoder;
import org.example.carrier.global.feign.FeignClientErrorDecoder;
import org.example.carrier.global.interceptor.ContentLengthRequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "org.example.carrier.global.feign")
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientErrorDecoder();
    }

    @Bean
    public ContentLengthRequestInterceptor contentLengthRequestInterceptor() {
        return new ContentLengthRequestInterceptor();
    }
}
