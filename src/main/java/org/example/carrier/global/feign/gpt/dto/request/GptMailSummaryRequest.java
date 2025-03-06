package org.example.carrier.global.feign.gpt.dto.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record GptMailSummaryRequest(
        String gmailId,
        String title,
        String data
) {
    public static GptBasicRequest of(ObjectMapper objectMapper, String gmailId, String title, String data) throws JsonProcessingException {
        GptMailSummaryRequest request = new GptMailSummaryRequest(gmailId, title, data);

        return GptBasicRequest.mailSummery(objectMapper, request);
    }
}
