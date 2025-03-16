package org.example.carrier.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UpdatePictureRequest(
        @NotNull(message = "picture이 비어있습니다.")
        MultipartFile picture
) {
}
