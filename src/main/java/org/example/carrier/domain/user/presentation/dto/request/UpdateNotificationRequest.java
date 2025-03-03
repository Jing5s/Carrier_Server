package org.example.carrier.domain.user.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record UpdateNotificationRequest(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime time
) {
}
