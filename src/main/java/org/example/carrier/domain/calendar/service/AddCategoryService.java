package org.example.carrier.domain.calendar.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.domain.repository.CategoryRepository;
import org.example.carrier.domain.calendar.presentation.dto.request.AddCategoryRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddCategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void execute(@Valid AddCategoryRequest request) {
        categoryRepository.save(request.toCategory());
    }
}
