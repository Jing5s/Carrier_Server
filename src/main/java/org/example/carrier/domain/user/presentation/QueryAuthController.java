package org.example.carrier.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.presentation.dto.request.ReissueRequest;
import org.example.carrier.domain.user.presentation.dto.request.TokenRequest;
import org.example.carrier.domain.user.presentation.dto.response.AccessTokenResponse;
import org.example.carrier.domain.user.service.QueryAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class QueryAuthController {
    private final QueryAuthService queryAuthService;

    @GetMapping
    public String getAuthLink(
            @RequestParam(required = false, defaultValue = "false") Boolean redirectUrl
    ) {
        return queryAuthService.getOAuthLink(redirectUrl);
    }

    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.CREATED)
    public AccessTokenResponse reissueToken(
            @Valid @RequestBody ReissueRequest reissueRequest
    ) {
        return queryAuthService.reissueToken(reissueRequest.token());
    }
}
