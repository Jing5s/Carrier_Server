package org.example.carrier.domain.calendar.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.domain.Category;
import org.example.carrier.domain.calendar.domain.repository.CategoryRepository;
import org.example.carrier.domain.calendar.domain.repository.ScheduleRepository;
import org.example.carrier.domain.calendar.exception.CategoryNotFoundException;
import org.example.carrier.domain.calendar.presentation.dto.request.AddScheduleRequest;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddScheduleService {
    private final CategoryRepository categoryRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void execute(@Valid AddScheduleRequest request, User cUser) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);

        scheduleRepository.save(request.toCalendar(cUser, category));
    }
}
