package org.example.carrier.global.feign.google;

import org.example.carrier.global.feign.google.dto.response.GoogleAccessToken;
import org.example.carrier.global.feign.google.dto.response.GoogleRefreshToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GoogleOAuthClient", url = "https://oauth2.googleapis.com")
public interface GoogleOAuthClient {
    @PostMapping("/token")
    GoogleRefreshToken getRefreshToken(
            @RequestParam String client_id,
            @RequestParam String client_secret,
            @RequestParam String code,
            @RequestParam String grant_type,
            @RequestParam String redirect_uri
    );

    @PostMapping("/token")
    GoogleAccessToken getAccessToken(
            @RequestParam String client_id,
            @RequestParam String client_secret,
            @RequestParam String refresh_token,
            @RequestParam String grant_type
    );
}
