package org.example.carrier.global.feign.gmail;

import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;
import org.example.carrier.global.feign.gmail.dto.response.GmailListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GmailAPIClient", url = "https://gmail.googleapis.com/gmail/v1")
public interface GmailAPIClient {
    @GetMapping("/users/me/messages")
    GmailListResponse getGmailList(@RequestParam String access_token);

    @GetMapping("/users/me/messages/{id}")
    GmailDetailResponse getGmailDetail(
            @PathVariable String id,
            @RequestParam String access_token
    );
}
