package org.example.carrier.domain.calendar.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateScheduleRequest(
        @NotNull(message = "id가 비어있습니다.")
        Long id,

        @NotEmpty(message = "title이 비어있습니다.")
        String title,

        @NotNull(message = "allDay가 비어있습니다.")
        Boolean allDay,

        @NotNull(message = "isRepeat이 비어있습니다.")
        Boolean isRepeat,

        String memo,

        @NotNull(message = "category이 비어있습니다.")
        Long categoryId,

        @NotNull(message = "startDate가 비어있습니다.")
        LocalDateTime startDate,

        LocalDateTime endDate,

        String location
) {
}
