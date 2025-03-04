package org.example.carrier.domain.diary.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import org.example.carrier.domain.diary.domain.Diary;
import org.example.carrier.domain.user.domain.User;

public record CreateDiaryRequest(
        @NotEmpty(message = "title이 비어있습니다.")
        String title,

        @NotEmpty(message = "content가 비어있습니다.")
        String content,

        @NotEmpty(message = "emoji가 비어있습니다.")
        String emoji
) {
    public Diary toDiary(User user) {
        return new Diary(title, content, emoji, user);
    }
}
