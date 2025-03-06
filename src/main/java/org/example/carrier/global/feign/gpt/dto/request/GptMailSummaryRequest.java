package org.example.carrier.global.feign.gpt.dto.request;

public record GptMailSummaryRequest(
        String gmailId,
        String title,
        String data
) implements GptBasicMessageRequest {
}
