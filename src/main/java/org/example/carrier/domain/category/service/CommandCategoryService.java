package org.example.carrier.domain.category.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.category.domain.repository.CategoryRepository;
import org.example.carrier.domain.category.exception.CategoryNotFoundException;
import org.example.carrier.domain.category.presentation.dto.request.AddCategoryRequest;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

@RequiredArgsConstructor
@CustomService
public class CommandCategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory(@Valid AddCategoryRequest request, User cUser) {
        categoryRepository.save(request.toCategory(cUser));
    }

    public void changeActiveStatus(Long id, User cUser) {
        Category category = categoryRepository.findByIdAndUser(id, cUser)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);

        category.changeActiveStatus();
    }
}
