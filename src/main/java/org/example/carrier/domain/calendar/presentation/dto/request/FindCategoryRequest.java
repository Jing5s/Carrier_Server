package org.example.carrier.domain.calendar.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record FindCategoryRequest(
        @NotNull(message = "startDate가 비어있습니다.")
        LocalDateTime startDate,

        @NotNull(message = "endDate가 비어있습니다.")
        LocalDateTime endDate
) {
}
