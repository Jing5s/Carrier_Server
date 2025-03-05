package org.example.carrier.global.feign.gpt.dto.response;

import java.time.LocalDateTime;

public record GptMailScheduleResponse(
        String title,
        Boolean allDay,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
