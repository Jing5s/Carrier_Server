package org.example.carrier.domain.calendar.presentation.dto.response;

import org.example.carrier.domain.calendar.domain.Category;
import org.example.carrier.domain.calendar.domain.type.Color;

public record CategoryResponse(
        Long id,
        String name,
        Color color
) {
    public CategoryResponse(Category category) {
        this(category.getId(), category.getName(), category.getColor());
    }
}
