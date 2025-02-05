package org.example.carrier.domain.calendar.presentation.dto.response;

import org.example.carrier.domain.calendar.domain.Category;
import org.example.carrier.domain.calendar.domain.type.Color;

public record CategoryResponse(
        String name,
        Color color
) {
    public CategoryResponse(Category category) {
        this(category.getName(), category.getColor());
    }
}
