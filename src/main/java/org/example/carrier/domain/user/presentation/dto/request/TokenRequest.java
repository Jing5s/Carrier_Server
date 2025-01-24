package org.example.carrier.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record TokenRequest(
        @NotNull(message = "token이 비어있습니다.")
        String token
) {
}
