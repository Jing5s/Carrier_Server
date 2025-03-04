package org.example.carrier.domain.user.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record UpdateNotificationRequest(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        @NotNull(message = "time이 비어있습니다.")
        LocalTime time
) {
}
