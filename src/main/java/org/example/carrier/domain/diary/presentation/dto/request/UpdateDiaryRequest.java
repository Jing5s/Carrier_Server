package org.example.carrier.domain.diary.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateDiaryRequest(
        @NotNull(message = "id가 비어있습니다.")
        Long id,

        @NotEmpty(message = "title이 비어있습니다.")
        String title,

        @NotEmpty(message = "content가 비어있습니다.")
        String content,

        @NotEmpty(message = "emoji가 비어있습니다.")
        String emoji
) {}
