package org.example.carrier.domain.calendar.presentation.dto.response;

import org.example.carrier.domain.calendar.domain.Schedule;

import java.time.LocalDateTime;

public record ScheduleResponse(
        String title,
        Boolean allDay,
        Boolean repeat,
        LocalDateTime startDate,
        LocalDateTime endDate,
        CategoryResponse category
) {
    public ScheduleResponse(Schedule schedule) {
        this(
                schedule.getTitle(), schedule.getAllDay(),
                schedule.getRepeat(), schedule.getStartDate(),
                schedule.getEndDate(), new CategoryResponse(schedule.getCategory())
        );
    }
}
