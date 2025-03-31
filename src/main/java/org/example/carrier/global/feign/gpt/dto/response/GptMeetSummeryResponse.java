package org.example.carrier.global.feign.gpt.dto.response;

public record GptMeetSummeryResponse(
        String title,
        String text
) implements GptMessageResponse {
}
