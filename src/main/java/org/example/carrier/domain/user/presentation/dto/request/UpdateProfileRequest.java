package org.example.carrier.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UpdateProfileRequest(
        @NotEmpty(message = "nickname이 비어있습니다.")
        String nickname
) {
}
