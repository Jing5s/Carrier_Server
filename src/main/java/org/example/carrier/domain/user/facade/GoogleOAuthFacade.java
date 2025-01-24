package org.example.carrier.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.GoogleAccessToken;
import org.example.carrier.domain.user.domain.RefreshToken;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.domain.repository.GoogleAccessTokenRepository;
import org.example.carrier.domain.user.domain.repository.RefreshTokenRepository;
import org.example.carrier.domain.user.exception.UserNotFoundException;
import org.example.carrier.global.config.properties.AuthProperties;
import org.example.carrier.global.feign.google.GoogleOAuthClient;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class GoogleOAuthFacade {
    private final GoogleAccessTokenRepository accessTokenRepository;
    private final GoogleOAuthClient googleOAuthClient;
    private final AuthProperties authProperties;

    public String getGoogleAccessToken(User user) {
        return accessTokenRepository.findById(user.getEmail())
                .map(GoogleAccessToken::getAccessToken)
                .orElseGet(() -> {
                    String newAccessToken = googleOAuthClient.getAccessToken(
                            authProperties.getClientId(),
                            authProperties.getClientSecret(),
                            user.getGoogleRefreshToken(),
                            "refresh_token"
                    ).access_token();

                    accessTokenRepository.save(new GoogleAccessToken(user.getEmail(), newAccessToken));
                    return newAccessToken;
                });
    }
}
