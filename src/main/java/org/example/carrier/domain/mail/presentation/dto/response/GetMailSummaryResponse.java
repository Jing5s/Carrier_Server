package org.example.carrier.domain.mail.presentation.dto.response;

public record GetMailSummaryResponse(
        String gmailId,
        String summary
) {
}
