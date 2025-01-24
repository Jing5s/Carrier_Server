package org.example.carrier.global.feign.google.dto.response;

public record GoogleRefreshTokenResponse(
        String access_token,
        String refresh_token
) {
}
