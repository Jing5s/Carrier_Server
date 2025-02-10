package org.example.carrier.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.category.domain.repository.CategoryRepository;
import org.example.carrier.domain.category.presentation.dto.response.CategoryResponse;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryCategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryResponse> getCategories(User cuser) {
        return categoryRepository.findAllByUser(cuser).stream()
                .map(CategoryResponse::new)
                .toList();
    }
}
