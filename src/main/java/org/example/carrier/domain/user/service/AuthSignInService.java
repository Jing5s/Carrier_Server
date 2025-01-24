package org.example.carrier.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.Token;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.domain.repository.TokenRepository;
import org.example.carrier.domain.user.domain.repository.UserRepository;
import org.example.carrier.domain.user.presentation.dto.request.TokenRequest;
import org.example.carrier.domain.user.presentation.dto.response.TokenResponse;
import org.example.carrier.global.config.properties.AuthProperties;
import org.example.carrier.global.feign.google.GoogleInformationClient;
import org.example.carrier.global.feign.google.GoogleOAuthClient;
import org.example.carrier.global.feign.google.dto.response.GoogleInformation;
import org.example.carrier.global.feign.google.dto.response.GoogleRefreshToken;
import org.example.carrier.global.security.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthSignInService {
    private final UserRepository userRepository;
    private final GoogleOAuthClient googleOAuthClient;
    private final GoogleInformationClient googleInformationClient;
    private final AuthProperties authProperties;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(TokenRequest tokenRequest) {
        GoogleRefreshToken googleToken = googleOAuthClient.getRefreshToken(
                authProperties.getClientId(),
                authProperties.getClientSecret(),
                tokenRequest.token(),
                "authorization_code",
                authProperties.getRedirectUrl()
        );

        GoogleInformation userInfo = googleInformationClient.getInformation(googleToken.access_token());
        String email = userInfo.email();
        Optional<User> user = userRepository.findByEmail(email);

        createUser(user, userInfo, googleToken.refresh_token());

        return new TokenResponse(
                jwtTokenProvider.createAccessToken(email),
                jwtTokenProvider.createRefreshToken(email, googleToken.access_token())
        );
    }

    private void createUser(Optional<User> user,
                            GoogleInformation userInfo,
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
