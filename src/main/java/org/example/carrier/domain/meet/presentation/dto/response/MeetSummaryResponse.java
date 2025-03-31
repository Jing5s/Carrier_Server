package org.example.carrier.domain.meet.presentation.dto.response;

import org.example.carrier.domain.meet.domain.Meet;

import java.time.LocalDateTime;

public record MeetSummaryResponse(
        Long id,
        String title,
        String text,
        String textSummary,
        String time,
        String audioLink,
        LocalDateTime createdAt
) {
    public static MeetSummaryResponse of(Meet meet) {
        return new MeetSummaryResponse(
                meet.getId(),
                meet.getTitle(),
                meet.getText(),
                meet.getSummaryText(),
                meet.getTime(),
                meet.getAudioLink(),
                meet.getCreatedAt()
        );
    }
}
