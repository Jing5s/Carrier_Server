package org.example.carrier.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record TokenRequest(
        @NotEmpty(message = "token이 비어있습니다.")
        String token,

        @NotEmpty(message = "redirectUrl이 비어있습니다.")
        String redirectUrl
) {
}
