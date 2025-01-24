package org.example.carrier.global.feign.google.dto.response;

public record GoogleInformationResponse(
        String email,
        String name,
        String picture
) {
}
