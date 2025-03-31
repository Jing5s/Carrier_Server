package org.example.carrier.domain.meet.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record MeetSummaryRequest(
        @NotNull(message = "file이 비어있습니다.")
        MultipartFile file
) {
}
