package org.example.carrier.domain.diary.presentation.dto.request;

import org.example.carrier.domain.diary.domain.Diary;
import org.example.carrier.domain.user.domain.User;

public record CreateDiaryRequest(
        String title,
        String content,
        String emoji
) {
    public Diary toDiary(User user) {
        return new Diary(title, content, emoji, user);
    }
}
