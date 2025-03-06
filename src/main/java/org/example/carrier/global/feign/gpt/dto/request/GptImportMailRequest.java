package org.example.carrier.global.feign.gpt.dto.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

public record GptImportMailRequest(
        String gmailId,
        String title,
        String from,
        String to,
        String subject,
        LocalDateTime date,
        Boolean isRead,
        List<String> labels
) {
    public static GptBasicRequest of(
            ObjectMapper objectMapper, String gmailId, String title, String from, String to,
            String subject, LocalDateTime date, Boolean isRead, List<String> labels) throws JsonProcessingException {
        GptImportMailRequest request = new GptImportMailRequest(
                gmailId, title, from, to, subject, date, isRead, labels);

        return GptBasicRequest.importantMail(objectMapper, request);
    }
}
