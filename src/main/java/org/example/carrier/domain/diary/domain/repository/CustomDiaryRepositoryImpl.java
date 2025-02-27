package org.example.carrier.domain.diary.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.diary.domain.Diary;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.carrier.domain.diary.domain.QDiary.diary;

@RequiredArgsConstructor
@Repository
public class CustomDiaryRepositoryImpl implements CustomDiaryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Diary> findAllDiaryByDateAndUser(LocalDateTime start, LocalDateTime end, User user) {
        return queryFactory
                .selectFrom(diary)
                .where(diary.createdAt.between(start, end)
                        .and(diary.user.eq(user)))
                .fetch();
    }
}
