package org.example.carrier.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.service.QueryAuthService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
