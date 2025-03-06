package org.example.carrier.global.feign.gpt;

import org.example.carrier.global.feign.gpt.dto.request.GptBasicRequest;
import org.example.carrier.global.feign.gpt.dto.response.GptBasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GptClient", url = "https://api.openai.com/v1/chat/completions")
public interface GptClient {
    @PostMapping
    GptBasicResponse getGptResponse(
            @RequestHeader("Authorization") String access_token,
            @RequestBody GptBasicRequest gptBasicRequest
    );
}
