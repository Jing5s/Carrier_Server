package org.example.carrier.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.RefreshToken;
import org.example.carrier.domain.user.domain.repository.RefreshTokenRepository;
import org.example.carrier.domain.user.presentation.dto.response.AccessTokenResponse;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.config.properties.AuthProperties;
import org.example.carrier.global.feign.exception.InvalidAuthTokenException;
import org.example.carrier.global.security.jwt.JwtTokenProvider;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryAuthService {
    private final AuthProperties properties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

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

    public AccessTokenResponse reissueToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(() -> InvalidAuthTokenException.EXCEPTION);

        return new AccessTokenResponse(
                jwtTokenProvider.createAccessToken(refreshToken.getEmail())
        );
    }
}
