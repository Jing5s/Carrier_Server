package org.example.carrier.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.global.config.properties.AuthProperties;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryAuthService {
    private final AuthProperties properties;

    public String getOAuthLink() {
        String scopeParam = String.join(" ", properties.getScopes());

        return String.format(
                "%s?client_id=%s&access_type=offline&prompt=consent&redirect_uri=%s&response_type=code&scope=%s",
                properties.getBaseUrl(),
                properties.getClientId(),
                properties.getRedirectUrl(),
                scopeParam
        );
    }
}
