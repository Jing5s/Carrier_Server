package org.example.carrier.domain.todo.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.example.carrier.domain.todo.domain.type.Priority;

import java.time.LocalDate;

public record UpdateTodoRequest(
        @NotNull(message = "id가 비어있습니다.")
        Long id,

        @NotEmpty(message = "title이 비어있습니다.")
        String title,

        @NotNull(message = "date가 비어있습니다.")
        LocalDate date,

        @NotNull(message = "isRepeat이 비어있습니다.")
        Boolean isRepeat,

        @NotNull(message = "priority가 비어있습니다.")
        Priority priority,

        String memo,

        String location
) {}
