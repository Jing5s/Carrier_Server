package org.example.carrier.domain.diary.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.diary.domain.repository.DiaryRepository;
import org.example.carrier.domain.diary.presentation.dto.request.CreateDiaryRequest;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

@RequiredArgsConstructor
@CustomService
public class CommandDiaryService {
    private final DiaryRepository diaryRepository;

    public Long createDiary(@Valid CreateDiaryRequest request, User cUser) {
        return diaryRepository.save(request.toDiary(cUser)).getId();
    }
}
