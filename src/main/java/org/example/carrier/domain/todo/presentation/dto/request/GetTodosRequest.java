package org.example.carrier.domain.todo.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record GetTodosRequest(
        @NotNull(message = "startDate가 비어있습니다.")
        LocalDate startDate,

        @NotNull(message = "endDate가 비어있습니다.")
        LocalDate endDate
) {
}
