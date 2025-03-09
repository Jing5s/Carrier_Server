package org.example.carrier.global.feign.gpt.dto.request.element;

import org.example.carrier.domain.mail.domain.Mail;

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
    public static MailElement from(Mail mail) {
        return new MailElement(
                mail.getGmailId(),
                mail.getTitle(),
                mail.getFrom(),
                mail.getTo(),
                mail.getSubject(),
                mail.getDate(),
                mail.getIsRead(),
                mail.getLabels()
        );
    }
}
