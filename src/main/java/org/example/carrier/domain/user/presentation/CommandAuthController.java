package org.example.carrier.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.presentation.dto.request.TokenRequest;
import org.example.carrier.domain.user.presentation.dto.response.AccessTokenResponse;
import org.example.carrier.domain.user.presentation.dto.response.TokenResponse;
import org.example.carrier.domain.user.service.CommandAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class CommandAuthController {
    private final CommandAuthService commandAuthService;

    @PostMapping("/signIn")
    public TokenResponse authSignIn(@Valid @RequestBody TokenRequest tokenRequest) {
        return commandAuthService.signIn(tokenRequest);
    }

    @PostMapping("/reissue")
    public AccessTokenResponse reissueToken(@Valid @RequestBody TokenRequest tokenRequest) {
        return commandAuthService.reissueToken(tokenRequest.token());
    }
}
