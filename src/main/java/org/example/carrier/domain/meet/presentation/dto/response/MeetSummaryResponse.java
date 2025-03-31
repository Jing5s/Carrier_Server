package org.example.carrier.domain.meet.presentation.dto.response;

import org.example.carrier.domain.meet.domain.Meet;

public record MeetSummaryResponse(
        String title,
        String text,
        String textSummary
) {
    public static MeetSummaryResponse of(Meet meet) {
        return new MeetSummaryResponse(
                meet.getTitle(),
                meet.getText(),
                meet.getSummaryText()
        );
    }
}
