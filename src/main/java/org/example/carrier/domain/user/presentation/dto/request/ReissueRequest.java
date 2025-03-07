package org.example.carrier.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record ReissueRequest(
        @NotEmpty(message = "token이 비어있습니다.")
        String token
) {
}
