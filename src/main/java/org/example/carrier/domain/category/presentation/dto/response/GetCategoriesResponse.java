package org.example.carrier.domain.category.presentation.dto.response;

import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.category.domain.type.Color;

public record GetCategoriesResponse(
        Long id,
        String name,
        Color color,
        Boolean active
) {
    public GetCategoriesResponse(Category category) {
        this(category.getId(), category.getName(), category.getColor(), category.getActive());
    }
}
