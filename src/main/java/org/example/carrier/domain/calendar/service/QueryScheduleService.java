package org.example.carrier.domain.calendar.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.category.domain.repository.CategoryRepository;
import org.example.carrier.domain.calendar.domain.repository.CustomScheduleRepository;
import org.example.carrier.domain.category.domain.exception.CategoryNotFoundException;
import org.example.carrier.domain.calendar.presentation.dto.request.FindCategoryRequest;
import org.example.carrier.domain.calendar.presentation.dto.response.ScheduleResponse;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryScheduleService {
    private final CategoryRepository categoryRepository;
    private final CustomScheduleRepository customScheduleRepository;

    @Transactional(readOnly = true)
    public List<ScheduleResponse> getSchedule(@Valid FindCategoryRequest request, User cUser) {
        List<Category> categories = request.categoryIds().stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> CategoryNotFoundException.EXCEPTION))
                .toList();

        return customScheduleRepository.findScheduleByDate(
                request.startDate(), request.endDate(),
                categories, cUser
        ).stream().map(ScheduleResponse::new).toList();
    }
}
