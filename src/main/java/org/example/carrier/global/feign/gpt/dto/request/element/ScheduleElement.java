package org.example.carrier.global.feign.gpt.dto.request.element;

import org.example.carrier.domain.calendar.domain.Schedule;
import org.example.carrier.domain.category.domain.type.Color;

import java.time.LocalDateTime;

public record ScheduleElement(
        String title,
        Boolean allDay,
        Boolean isRepeat,
        Long categoryId,
        Color categoryColor,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String location
) {
    public static ScheduleElement from(Schedule schedule) {
        return new ScheduleElement(
                schedule.getTitle(),
                schedule.getAllDay(),
                schedule.getIsRepeat(),
                schedule.getCategory().getId(),
                schedule.getCategory().getColor(),
                schedule.getStartDate(),
                schedule.getEndDate(),
                schedule.getLocation()
        );
    }
}
