package org.example.carrier.global.feign.gpt;

import org.example.carrier.global.feign.gpt.dto.request.GptImportMailRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptMailScheduleRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptMailSummaryRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptTodayTipsRequest;
import org.example.carrier.global.feign.gpt.dto.response.GptImportMailResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptMailScheduleResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptMailSummaryResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptTodayTipsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GptClient", url = "https://api.openai.com/v1/chat/completions")
public interface GptClient {
    @PostMapping
    GptImportMailResponse getImportantMail(
            @RequestHeader("Authorization") String access_token,
            @RequestBody GptImportMailRequest gptImportMailRequest
    );

    @PostMapping
    GptMailScheduleResponse getMailSchedule(
            @RequestHeader("Authorization") String access_token,
            @RequestBody GptMailScheduleRequest gptMailScheduleRequest
    );

    @PostMapping
    GptMailSummaryResponse getMailSummary(
            @RequestHeader("Authorization") String access_token,
            @RequestBody GptMailSummaryRequest gptMailSummaryRequest
    );

    @PostMapping
    GptTodayTipsResponse getTodayTips(
            @RequestHeader("Authorization") String access_token,
            @RequestBody GptTodayTipsRequest gptTodayTipsRequest
    );
}
