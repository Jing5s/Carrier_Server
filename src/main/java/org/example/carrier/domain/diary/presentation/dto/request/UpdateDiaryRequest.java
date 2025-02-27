package org.example.carrier.domain.diary.presentation.dto.request;

public record UpdateDiaryRequest(
        Long id,
        String title,
        String content,
        String emoji
) {}
