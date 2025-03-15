package org.example.carrier.domain.calendar.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.domain.Schedule;
import org.example.carrier.domain.calendar.domain.repository.ScheduleRepository;
import org.example.carrier.domain.calendar.exception.ScheduleNotFoundException;
import org.example.carrier.domain.calendar.presentation.dto.request.AddScheduleRequest;
import org.example.carrier.domain.calendar.presentation.dto.request.UpdateScheduleRequest;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.category.domain.repository.CategoryRepository;
import org.example.carrier.domain.category.exception.CategoryNotFoundException;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

@RequiredArgsConstructor
@CustomService
public class CommandScheduleService {
    private final CategoryRepository categoryRepository;
    private final ScheduleRepository scheduleRepository;

    public void createSchedule(@Valid AddScheduleRequest request, User cUser) {
        Category category = categoryRepository.findByIdAndUser(request.categoryId(), cUser)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);

        scheduleRepository.save(request.toCalendar(cUser, category));
    }

    public void updateSchedule(@Valid UpdateScheduleRequest request, User cUser) {
        Category category = categoryRepository.findByIdAndUser(request.categoryId(), cUser)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);

        Schedule schedule = scheduleRepository.findByIdAndUser(request.id(), cUser)
                .orElseThrow(() -> ScheduleNotFoundException.EXCEPTION);

        schedule.update(request.title(), request.allDay(), request.isRepeat(),
                request.memo(), request.startDate(), request.endDate(),
                request.location(), category);
    }

    public void deleteSchedule(Long id, User cUser) {
        Schedule schedule = scheduleRepository.findByIdAndUser(id, cUser)
                .orElseThrow(() -> ScheduleNotFoundException.EXCEPTION);

        scheduleRepository.delete(schedule);
    }
}
