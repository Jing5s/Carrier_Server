package org.example.carrier.domain.calendar.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.category.domain.repository.CategoryRepository;
import org.example.carrier.domain.calendar.domain.repository.ScheduleRepository;
import org.example.carrier.domain.category.exception.CategoryNotFoundException;
import org.example.carrier.domain.calendar.presentation.dto.request.AddScheduleRequest;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommandScheduleService {
    private final CategoryRepository categoryRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void createSchedule(@Valid AddScheduleRequest request, User cUser) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);

        scheduleRepository.save(request.toCalendar(cUser, category));
    }
}
