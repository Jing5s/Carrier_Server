package org.example.carrier.domain.category.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.example.carrier.domain.category.domain.type.Color;

public record UpdateCategoryRequest(
        @NotNull(message = "message가 비어있습니다.")
        Long id,

        @NotEmpty(message = "name이 비어있습니다.")
        String name,

        @NotNull(message = "color가 비어있습니다.")
        Color color
) {
}
