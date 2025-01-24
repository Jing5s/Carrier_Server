package org.example.carrier.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.RefreshToken;
import org.example.carrier.domain.user.domain.repository.RefreshTokenRepository;
import org.example.carrier.domain.user.presentation.dto.response.AccessTokenResponse;
import org.example.carrier.global.feign.exception.InvalidAuthTokenException;
import org.example.carrier.global.security.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReissueTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public AccessTokenResponse execute(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(() -> InvalidAuthTokenException.EXCEPTION);

        return new AccessTokenResponse(
                jwtTokenProvider.createAccessToken(refreshToken.getEmail())
        );
    }
}
