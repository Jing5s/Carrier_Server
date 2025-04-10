package org.example.carrier.domain.diary.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.diary.domain.Diary;
import org.example.carrier.domain.diary.domain.repository.DiaryRepository;
import org.example.carrier.domain.diary.exception.DiaryIsWriteException;
import org.example.carrier.domain.diary.exception.DiaryNotFoundException;
import org.example.carrier.domain.diary.presentation.dto.request.CreateDiaryRequest;
import org.example.carrier.domain.diary.presentation.dto.request.UpdateDiaryRequest;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

import java.time.LocalDate;

@RequiredArgsConstructor
@CustomService
public class CommandDiaryService {
    private final DiaryRepository diaryRepository;

    public Long createDiary(@Valid CreateDiaryRequest request, User cUser) {
        Boolean isWrite = diaryRepository.existsByDateAndUser(LocalDate.now(), cUser);
        if (isWrite) { throw DiaryIsWriteException.EXCEPTION; }

        return diaryRepository.save(request.toDiary(cUser)).getId();
    }

    public void updateDiary(@Valid UpdateDiaryRequest request, User cUser) {
        Diary diary = diaryRepository.findByIdAndUser(request.id(), cUser)
                .orElseThrow(() -> DiaryNotFoundException.EXCEPTION);

        diary.update(request.title(), request.content(), request.emoji());
    }

    public void deleteDiary(Long id, User cUser) {
        Diary diary = diaryRepository.findByIdAndUser(id, cUser)
                .orElseThrow(() -> DiaryNotFoundException.EXCEPTION);

        diaryRepository.delete(diary);
    }
}
