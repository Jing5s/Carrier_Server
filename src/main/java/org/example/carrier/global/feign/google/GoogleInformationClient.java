package org.example.carrier.global.feign.google;

import org.example.carrier.global.feign.google.dto.response.GoogleInformationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GoogleInformationClient", url = "https://www.googleapis.com")
public interface GoogleInformationClient {
    @GetMapping("/oauth2/v1/userinfo")
    GoogleInformationResponse getInformation(@RequestParam String access_token);

    @GetMapping("/oauth2/v1/tokeninfo")
    Object getTokenInfo(@RequestParam String access_token);
}
