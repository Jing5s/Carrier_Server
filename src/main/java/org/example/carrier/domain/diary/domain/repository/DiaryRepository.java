package org.example.carrier.domain.diary.domain.repository;

import org.example.carrier.domain.diary.domain.Diary;
import org.example.carrier.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long>, CustomDiaryRepository {
    Optional<Diary> findByIdAndUser(Long id, User user);
    Boolean existsByDate(LocalDate date);
}
