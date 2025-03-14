package org.example.carrier.global.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("nextcloud")
public class NextCloudProperties {
    private final String baseUrl;
    private final String username;
    private final String password;
}
