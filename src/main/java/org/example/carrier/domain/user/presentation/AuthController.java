package org.example.carrier.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.service.GenerateOAuthLinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final GenerateOAuthLinkService generateOAuthLinkService;

    @GetMapping
    public String getAuthLink() {
        return generateOAuthLinkService.execute();
    }
}
