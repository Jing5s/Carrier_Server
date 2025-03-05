package org.example.carrier.global.feign.gpt.dto.request.element;

import java.time.LocalDateTime;
import java.util.List;

public record MailElement(
        String gmailId,
        String title,
        String from,
        String to,
        String subject,
        LocalDateTime date,
        Boolean isRead,
        List<String> labels
) {
}
