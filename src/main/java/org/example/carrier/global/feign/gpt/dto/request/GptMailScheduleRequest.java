package org.example.carrier.global.feign.gpt.dto.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record GptMailScheduleRequest(
        String gmailId,
        String title,
        String data
) {
    public static GptBasicRequest of(ObjectMapper objectMapper, String gmailId, String title, String data) throws JsonProcessingException {
        GptMailScheduleRequest request = new GptMailScheduleRequest(gmailId, title, data);

        return GptBasicRequest.mailSchedule(objectMapper, request);
    }
}
