package org.example.carrier.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UpdateProfileRequest(
        @NotEmpty(message = "nickname이 비어있습니다.")
        String nickname,

        @NotNull(message = "picture이 비어있습니다.")
        MultipartFile picture
) {
}
