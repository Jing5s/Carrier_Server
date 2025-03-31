package org.example.carrier.global.feign.gpt.dto.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.carrier.global.feign.gpt.dto.request.element.MailElement;
import org.example.carrier.global.feign.gpt.dto.request.element.ScheduleElement;
import org.example.carrier.global.feign.gpt.dto.request.element.TodoElement;

import java.util.List;

public record GptMeetSummaryRequest(
        String text
) {
    public static GptBasicRequest of(ObjectMapper objectMapper, String text) throws JsonProcessingException {
        GptMeetSummaryRequest request = new GptMeetSummaryRequest(text);

        return GptBasicRequest.meetSummary(objectMapper, request);
    }
}
