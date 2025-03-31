package org.example.carrier.domain.meet.presentation.dto.response;

import org.example.carrier.domain.meet.domain.Meet;

import java.time.LocalDateTime;

public record GetMeetsResponse(
        Long id,
        String title,
        String time,
        LocalDateTime createdAt
) {
    public static GetMeetsResponse of(Meet meet) {
        return new GetMeetsResponse(
                meet.getId(),
                meet.getTitle(),
                meet.getTime(),
                meet.getCreatedAt()
        );
    }
}
