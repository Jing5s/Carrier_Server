package org.example.carrier.domain.diary.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.diary.domain.Diary;
import org.example.carrier.domain.diary.domain.repository.DiaryRepository;
import org.example.carrier.domain.diary.exception.DiaryNotFoundException;
import org.example.carrier.domain.diary.presentation.dto.response.DiaryResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryDiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryResponse getDiary(Long id, User cUser) {
        Diary diary = diaryRepository.findByIdAndUser(id, cUser)
                .orElseThrow(() -> DiaryNotFoundException.EXCEPTION);

        return new DiaryResponse(diary);
    }
}
