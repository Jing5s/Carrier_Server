package org.example.carrier.domain.calendar.presentation.dto.response;

import org.example.carrier.domain.calendar.domain.Schedule;
import org.example.carrier.domain.category.presentation.dto.response.GetCategoriesResponse;

import java.time.LocalDateTime;

public record GetSchedulesResponse(
        Long id,
        String title,
        Boolean allDay,
        Boolean isRepeat,
        String memo,
        LocalDateTime startDate,
        LocalDateTime endDate,
        GetCategoriesResponse category,
        String location
) {
    public GetSchedulesResponse(Schedule schedule) {
        this(
                schedule.getId(), schedule.getTitle(), schedule.getAllDay(), schedule.getIsRepeat(),
                schedule.getMemo(), schedule.getStartDate(), schedule.getEndDate(),
                new GetCategoriesResponse(schedule.getCategory()), schedule.getLocation()
        );
    }
}
