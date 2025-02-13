package org.example.carrier.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.GoogleAccessToken;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.domain.repository.GoogleAccessTokenRepository;
import org.example.carrier.domain.user.domain.repository.UserRepository;
import org.example.carrier.domain.user.presentation.dto.request.TokenRequest;
import org.example.carrier.domain.user.presentation.dto.response.TokenResponse;
import org.example.carrier.global.config.properties.AuthProperties;
import org.example.carrier.global.feign.google.GoogleInformationClient;
import org.example.carrier.global.feign.google.GoogleOAuthClient;
import org.example.carrier.global.feign.google.dto.response.GoogleInformationResponse;
import org.example.carrier.global.feign.google.dto.response.GoogleRefreshTokenResponse;
import org.example.carrier.global.security.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommandAuthService {
    private final UserRepository userRepository;
    private final GoogleOAuthClient googleOAuthClient;
    private final GoogleInformationClient googleInformationClient;
    private final AuthProperties authProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private final GoogleAccessTokenRepository googleAccessTokenRepository;

    @Transactional
    public TokenResponse signIn(TokenRequest tokenRequest) {
        GoogleRefreshTokenResponse googleToken = googleOAuthClient.getRefreshToken(
                authProperties.getClientId(),
                authProperties.getClientSecret(),
                tokenRequest.token(),
                "authorization_code",
                authProperties.getRedirectUrl()
        );

        GoogleInformationResponse userInfo = googleInformationClient.getInformation(googleToken.access_token());
        String email = userInfo.email();
        Optional<User> user = userRepository.findByEmail(email);

        createUser(user, userInfo, googleToken.refresh_token());

        googleAccessTokenRepository.save(new GoogleAccessToken(email, googleToken.access_token()));

        return new TokenResponse(
                jwtTokenProvider.createAccessToken(email),
                jwtTokenProvider.createRefreshToken(email)
        );
    }

    private void createUser(Optional<User> user,
                            GoogleInformationResponse userInfo,
                            String refreshToken
    ) {
        if (user.isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(userInfo.email())
                            .nickname(userInfo.name())
                            .picture(userInfo.picture())
                            .googleRefreshToken(refreshToken)
                            .build()
            );
        }
    }
}
