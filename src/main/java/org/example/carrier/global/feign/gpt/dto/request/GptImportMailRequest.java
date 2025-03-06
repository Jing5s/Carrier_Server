package org.example.carrier.global.feign.gpt.dto.request;

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
) implements GptBasicMessageRequest {
}
