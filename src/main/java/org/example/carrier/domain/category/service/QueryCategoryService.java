package org.example.carrier.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.category.domain.repository.CategoryRepository;
import org.example.carrier.domain.category.presentation.dto.response.CategoryResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

import java.util.List;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryCategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getCategories(User cuser) {
        return categoryRepository.findAllByUser(cuser).stream()
                .map(CategoryResponse::new)
                .toList();
    }
}
