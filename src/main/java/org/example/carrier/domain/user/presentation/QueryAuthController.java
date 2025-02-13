package org.example.carrier.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.presentation.dto.request.TokenRequest;
import org.example.carrier.domain.user.presentation.dto.response.AccessTokenResponse;
import org.example.carrier.domain.user.service.QueryAuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class QueryAuthController {
    private final QueryAuthService queryAuthService;

    @GetMapping
    public String getAuthLink() {
        return queryAuthService.getOAuthLink();
    }

    @PostMapping("/reissue")
    public AccessTokenResponse reissueToken(@Valid @RequestBody TokenRequest tokenRequest) {
        return queryAuthService.reissueToken(tokenRequest.token());
    }
}
