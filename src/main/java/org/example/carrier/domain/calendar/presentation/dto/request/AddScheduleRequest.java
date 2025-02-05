package org.example.carrier.domain.calendar.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import org.example.carrier.domain.calendar.domain.Category;
import org.example.carrier.domain.calendar.domain.Schedule;
import org.example.carrier.domain.user.domain.User;

import java.time.LocalDateTime;

public record AddScheduleRequest(
        @NotEmpty(message = "token이 비어있습니다.")
        String title,

        @NotEmpty(message = "allDay가 비어있습니다.")
        Boolean allDay,

        @NotEmpty(message = "repeat이 비어있습니다.")
        Boolean repeat,

        @NotEmpty(message = "category이 비어있습니다.")
        Long categoryId,

        LocalDateTime startDate,

        LocalDateTime endDate,

        String location
) {
    public Schedule toCalendar(User user, Category category) {
        return Schedule.builder()
                .title(title)
                .allDay(allDay)
                .repeat(repeat)
                .startDate(startDate)
                .endDate(endDate)
                .location(location)
                .user(user)
                .category(category)
                .build();
    }
}
