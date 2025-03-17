package org.example.carrier.global.feign;

import feign.Request;
import feign.codec.ErrorDecoder;
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

    @Bean
    public Request.Options feignOptions() {
        return new Request.Options(30_000, 30_000); // 10초 연결 타임아웃, 30초 읽기 타임아웃
    }

}
