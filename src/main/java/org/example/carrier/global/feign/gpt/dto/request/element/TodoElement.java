package org.example.carrier.global.feign.gpt.dto.request.element;

public record TodoElement(
        Long id,
        String title,
        Boolean isDone
) {
}
