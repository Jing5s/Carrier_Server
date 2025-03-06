package org.example.carrier.global.feign.gpt.dto.response;

public record GptMailSummaryResponse(
        String gmailId,
        String summary
) implements GptMessageResponse {
}
