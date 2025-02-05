package org.example.carrier.domain.calendar.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.domain.repository.CategoryRepository;
import org.example.carrier.domain.calendar.presentation.dto.response.CategoryResponse;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListCategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryResponse> execute(User user) {
        return categoryRepository.findAllByUser(user).stream()
                .map(CategoryResponse::new)
                .toList();
    }
}
