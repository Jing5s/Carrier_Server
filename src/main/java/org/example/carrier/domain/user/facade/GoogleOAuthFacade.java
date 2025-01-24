package org.example.carrier.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.Token;
import org.example.carrier.domain.user.domain.repository.TokenRepository;
import org.example.carrier.domain.user.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GoogleOAuthFacade {
    private final TokenRepository tokenRepository;

//    public String getAuthAccessToken(String email) {
//        Token token = findTokenByEmail(email);
//    }

    public Token findTokenByEmail(String email) {
        return tokenRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
