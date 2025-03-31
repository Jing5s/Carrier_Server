package org.example.carrier.global.feign.gpt;

import org.example.carrier.global.feign.gpt.dto.request.GptBasicRequest;
import org.example.carrier.global.feign.gpt.dto.response.GptBasicResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptMeetTextResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "GptClient", url = "https://api.openai.com/v1")
public interface GptClient {
    @PostMapping("/chat/completions")
    GptBasicResponse getGptResponse(
            @RequestHeader("Authorization") String access_token,
            @RequestBody GptBasicRequest gptBasicRequest
    );

    @PostMapping(value = "/audio/transcriptions", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    GptMeetTextResponse getMeetText(
            @RequestHeader("Authorization") String access_token,
            @RequestPart("file") MultipartFile file
    );
}
