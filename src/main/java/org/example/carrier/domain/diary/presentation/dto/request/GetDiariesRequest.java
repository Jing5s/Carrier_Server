package org.example.carrier.domain.diary.presentation.dto.request;

import java.time.LocalDateTime;

public record GetDiariesRequest(
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {
}
