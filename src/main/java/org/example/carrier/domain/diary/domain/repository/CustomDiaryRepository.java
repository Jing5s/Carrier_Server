package org.example.carrier.domain.diary.domain.repository;

import org.example.carrier.domain.diary.domain.Diary;
import org.example.carrier.domain.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomDiaryRepository {
    List<Diary> findAllDiaryByDateAndUser(LocalDateTime start, LocalDateTime end, User user);
}
