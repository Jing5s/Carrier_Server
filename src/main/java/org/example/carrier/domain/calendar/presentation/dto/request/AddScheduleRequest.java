package org.example.carrier.domain.calendar.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.calendar.domain.Schedule;
import org.example.carrier.domain.user.domain.User;

import java.time.LocalDateTime;

public record AddScheduleRequest(
        @NotEmpty(message = "title이 비어있습니다.")
        String title,

        @NotNull(message = "allDay가 비어있습니다.")
        Boolean allDay,

        @NotNull(message = "isRepeat이 비어있습니다.")
        Boolean isRepeat,

        @NotNull(message = "category이 비어있습니다.")
        Long categoryId,

        @NotNull(message = "startDate가 비어있습니다.")
        LocalDateTime startDate,

        LocalDateTime endDate,

        String location
) {
    public Schedule toCalendar(User user, Category category) {
        return Schedule.builder()
                .title(title)
                .allDay(allDay)
                .isRepeat(isRepeat)
                .startDate(startDate)
                .endDate(endDate)
                .location(location)
                .user(user)
                .category(category)
                .build();
    }
}
