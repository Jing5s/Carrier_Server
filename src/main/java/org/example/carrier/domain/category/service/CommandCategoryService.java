package org.example.carrier.domain.category.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.category.domain.repository.CategoryRepository;
import org.example.carrier.domain.category.presentation.dto.request.AddCategoryRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommandCategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void createCategory(@Valid AddCategoryRequest request) {
        categoryRepository.save(request.toCategory());
    }
}
