package org.example.carrier.global.feign.google.dto.response;

public record GoogleInformation(
        String email,
        String name,
        String picture
) {
}
