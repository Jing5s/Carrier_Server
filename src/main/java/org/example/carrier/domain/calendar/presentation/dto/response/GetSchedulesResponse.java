package org.example.carrier.domain.calendar.presentation.dto.response;

import org.example.carrier.domain.calendar.domain.Schedule;
import org.example.carrier.domain.category.presentation.dto.response.GetCategoriesResponse;

import java.time.LocalDateTime;

public record GetSchedulesResponse(
        String title,
        Boolean allDay,
        Boolean isRepeat,
        LocalDateTime startDate,
        LocalDateTime endDate,
        GetCategoriesResponse category
) {
    public GetSchedulesResponse(Schedule schedule) {
        this(
                schedule.getTitle(), schedule.getAllDay(),
                schedule.getIsRepeat(), schedule.getStartDate(),
                schedule.getEndDate(), new GetCategoriesResponse(schedule.getCategory())
        );
    }
}
