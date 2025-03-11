package org.example.carrier.domain.calendar.domain.repository;

import org.example.carrier.domain.calendar.domain.Schedule;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomScheduleRepository {
    List<Schedule> findScheduleByDate(LocalDateTime startDate, LocalDateTime endDate, User user);
    List<Schedule> findScheduleByDateAndCategories(LocalDateTime startDate, LocalDateTime endDate, List<Category> categories, User user);
}
