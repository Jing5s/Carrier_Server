package org.example.carrier.domain.meet.presentation.dto.response;

import org.example.carrier.domain.meet.domain.Meet;

import java.time.LocalDateTime;

public record GetMeetsResponse(
        Long id,
        String title,
        String text,
        String textSummary,
        String time,
        String audioLink,
        LocalDateTime createdAt
) {
    public static GetMeetsResponse of(Meet meet) {
        return new GetMeetsResponse(
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
