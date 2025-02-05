package org.example.carrier.domain.calendar.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

public record FindCategoryRequest(
        @NotEmpty(message = "startDate가 비어있습니다.")
        LocalDateTime startDate,

        @NotEmpty(message = "endDate가 비어있습니다.")
        LocalDateTime endDate,

        @NotEmpty(message = "categoryIds가 비어있습니다.")
        List<Long> categoryIds
) {
}
