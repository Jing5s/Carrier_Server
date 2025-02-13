package org.example.carrier.domain.category.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.category.domain.type.Color;
import org.example.carrier.domain.user.domain.User;

public record AddCategoryRequest(
        @NotEmpty(message = "name이 비어있습니다.")
        String name,

        @NotEmpty(message = "color가 비어있습니다.")
        Color color
) {
    public Category toCategory(User user) {
        return new Category(name, color, user);
    }
}
