package org.example.carrier.domain.mail.presentation.dto.response;

import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.global.feign.gmail.dto.response.element.GmailPayload;

import java.time.LocalDateTime;
import java.util.List;

public record GetMailResponse(
        String gmailId,
        String title,
        String from,
        String to,
        String subject,
        LocalDateTime date,
        Boolean isRead,
        List<String> labels,
        String body,
        String summary
) {
    public static GetMailResponse of(Mail mail, GmailPayload payload) {
        return new GetMailResponse(
                mail.getGmailId(),
                mail.getTitle(),
                mail.getFrom(),
                mail.getTo(),
                mail.getSubject(),
                mail.getDate(),
                mail.getIsRead(),
                List.copyOf(mail.getLabels()),
                payload.getBody(),
                mail.getSummary()
        );
    }
}
