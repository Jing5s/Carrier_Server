package org.example.carrier.domain.user.presentation.dto.response;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
