package org.example.carrier.domain.diary.presentation.dto.response;

import org.example.carrier.domain.diary.domain.Diary;

import java.time.LocalDateTime;

public record DiaryResponse(
        Long id,
        String title,
        String content,
        String emoji,
        LocalDateTime createDateTime
) {
    public DiaryResponse(Diary diary) {
        this(diary.getId(), diary.getTitle(), diary.getContent(), diary.getEmoji(), diary.getCreatedAt());
    }
}
