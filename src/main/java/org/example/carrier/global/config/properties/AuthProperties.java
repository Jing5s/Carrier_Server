package org.example.carrier.global.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("auth")
public class AuthProperties {
    private final String baseUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;
    private final String[] scopes;
}
