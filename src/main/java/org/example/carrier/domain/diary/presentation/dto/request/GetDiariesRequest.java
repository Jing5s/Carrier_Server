package org.example.carrier.domain.diary.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record GetDiariesRequest(
        @NotNull(message = "startDateTime가 비어있습니다.")
        LocalDateTime startDateTime,

        @NotNull(message = "endDateTime가 비어있습니다.")
        LocalDateTime endDateTime
) {
}
