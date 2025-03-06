package org.example.carrier.domain.mail.presentation.dto.response;

import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.global.feign.gmail.dto.response.element.GmailPayload;

import java.time.LocalDateTime;
import java.util.List;

public record GetMailsResponse(
        String gmailId,
        String title,
        String from,
        String to,
        String subject,
        String preview,
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
                mail.getPreview(),
                mail.getDate(),
                mail.getIsRead(),
                mail.getLabels()
        );
    }
}
