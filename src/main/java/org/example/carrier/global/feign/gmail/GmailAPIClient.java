package org.example.carrier.global.feign.gmail;

import org.example.carrier.global.feign.gmail.dto.request.ModifyLabelRequest;
import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;
import org.example.carrier.global.feign.gmail.dto.response.GmailHistoryResponse;
import org.example.carrier.global.feign.gmail.dto.response.GmailListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GmailAPIClient", url = "https://gmail.googleapis.com/gmail/v1")
public interface GmailAPIClient {
    @GetMapping("/users/me/messages")
    GmailListResponse getGmailList(
            @RequestParam String access_token
    );

    @GetMapping("/users/me/messages/{id}")
    GmailDetailResponse getGmailDetail(
            @PathVariable String id,
            @RequestParam String access_token
    );

    @GetMapping("/users/me/history")
    GmailHistoryResponse getHistory(
            @RequestParam String historyTypes,
            @RequestParam Long startHistoryId,
            @RequestParam String access_token
    );

    @PostMapping("users/me/messages/{messageId}/modify")
    Object modifyLabels(
            @PathVariable String messageId,
            @RequestBody ModifyLabelRequest request,
            @RequestHeader("Authorization") String access_token
    );
}
