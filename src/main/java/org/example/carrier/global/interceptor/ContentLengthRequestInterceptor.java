package org.example.carrier.global.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class ContentLengthRequestInterceptor implements RequestInterceptor {

    private static final String CONTENT_LENGTH_HEADER = "Content-Length";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        var length = requestTemplate.body() != null ? requestTemplate.body().length : 0;
        if (length == 0 && requestTemplate.method().equalsIgnoreCase("POST")) {
            String body = "hello";
            requestTemplate.body(body);
            requestTemplate.header(CONTENT_LENGTH_HEADER, String.valueOf(body.getBytes().length));
        }
    }
}
