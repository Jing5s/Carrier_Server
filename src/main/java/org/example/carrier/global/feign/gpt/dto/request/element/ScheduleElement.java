package org.example.carrier.global.feign.gpt.dto.request.element;

import org.example.carrier.domain.category.presentation.dto.response.GetCategoriesResponse;

import java.time.LocalDateTime;

public record ScheduleElement(
        String title,
        Boolean allDay,
        Boolean isRepeat,
        LocalDateTime startDate,
        LocalDateTime endDate,
        GetCategoriesResponse category
) {
}
