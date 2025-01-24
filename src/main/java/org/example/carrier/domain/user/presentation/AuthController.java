package org.example.carrier.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.facade.GoogleOAuthFacade;
import org.example.carrier.domain.user.facade.UserFacade;
import org.example.carrier.domain.user.presentation.dto.request.TokenRequest;
import org.example.carrier.domain.user.presentation.dto.response.AccessTokenResponse;
import org.example.carrier.domain.user.presentation.dto.response.TokenResponse;
import org.example.carrier.domain.user.service.AuthSignInService;
import org.example.carrier.domain.user.service.GenerateOAuthLinkService;
import org.example.carrier.domain.user.service.ReissueTokenService;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final GenerateOAuthLinkService generateOAuthLinkService;
    private final AuthSignInService authSignInService;
    private final ReissueTokenService reissueTokenService;

    @GetMapping
    public String getAuthLink() {
        return generateOAuthLinkService.execute();
    }

    @PostMapping("/signIn")
    public TokenResponse authSignIn(@Valid @RequestBody TokenRequest tokenRequest) {
        return authSignInService.execute(tokenRequest);
    }

    @PostMapping("/reissue")
    public AccessTokenResponse reissueToken(@Valid @RequestBody TokenRequest tokenRequest) {
        return reissueTokenService.execute(tokenRequest.token());
    }
}
