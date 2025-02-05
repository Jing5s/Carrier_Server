package org.example.carrier.domain.calendar.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import org.example.carrier.domain.calendar.domain.Category;
import org.example.carrier.domain.calendar.domain.type.Color;

public record AddCategoryRequest(
        @NotEmpty(message = "name이 비어있습니다.")
        String name,

        @NotEmpty(message = "color가 비어있습니다.")
        Color color
) {
    public Category toCategory() {
        return new Category(name, color);
    }
}
