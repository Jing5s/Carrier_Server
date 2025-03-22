package org.example.carrier.domain.diary.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DiaryRecommendRequest(
        @NotNull(message = "date가 비어있습니다.")
        LocalDate date
) {
}
