package org.example.carrier.domain.mail.presentation.dto.response;

import org.example.carrier.domain.mail.domain.Mail;

import java.time.LocalDateTime;
import java.util.List;

public record GetMailsResponse(
        String gmailId,
        String title,
        String from,
        String to,
        String subject,
        LocalDateTime date,
        Boolean isRead,
        List<String> labels
) {
    public static GetMailsResponse of(Mail mail) {
        return new GetMailsResponse(
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
