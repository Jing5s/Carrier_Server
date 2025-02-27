package org.example.carrier.domain.category.presentation.dto.response;

import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.category.domain.type.Color;

public record CategoryResponse(
        Long id,
        String name,
        Color color,
        Boolean active
) {
    public CategoryResponse(Category category) {
        this(category.getId(), category.getName(), category.getColor(), category.getActive());
    }
}
