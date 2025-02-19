package org.example.carrier.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.category.domain.repository.CategoryRepository;
import org.example.carrier.domain.category.domain.type.Color;
import org.example.carrier.domain.user.domain.GoogleAccessToken;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.domain.repository.GoogleAccessTokenRepository;
import org.example.carrier.domain.user.domain.repository.UserRepository;
import org.example.carrier.domain.user.facade.GoogleOAuthFacade;
import org.example.carrier.domain.user.presentation.dto.request.TokenRequest;
import org.example.carrier.domain.user.presentation.dto.response.TokenResponse;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.feign.google.GoogleInformationClient;
import org.example.carrier.global.feign.google.dto.response.GoogleInformationResponse;
import org.example.carrier.global.feign.google.dto.response.GoogleRefreshTokenResponse;
import org.example.carrier.global.security.jwt.JwtTokenProvider;

import java.util.Optional;

@RequiredArgsConstructor
@CustomService
public class CommandAuthService {
    private final GoogleOAuthFacade googleOAuthFacade;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final CategoryRepository categoryRepository;
    private final GoogleInformationClient googleInformationClient;
    private final GoogleAccessTokenRepository googleAccessTokenRepository;

    static final String categoryBasicTitle = "나의 일정";

    public TokenResponse signIn(TokenRequest tokenRequest) {
        GoogleRefreshTokenResponse googleToken
                = googleOAuthFacade.getGoogleRefreshToken(tokenRequest.token());

        GoogleInformationResponse userInfo
                = googleInformationClient.getInformation(googleToken.access_token());

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
            User newUser = userRepository.save(
                    User.builder()
                            .email(userInfo.email())
                            .nickname(userInfo.name())
                            .picture(userInfo.picture())
                            .googleRefreshToken(refreshToken)
                            .build()
            );

            createCategory(newUser);
        }
    }

    private void createCategory(User user) {
        categoryRepository.save(
                new Category(categoryBasicTitle, Color.BLUE, user)
        );
    }
}
