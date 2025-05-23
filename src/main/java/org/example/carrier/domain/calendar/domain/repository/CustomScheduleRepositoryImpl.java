package org.example.carrier.domain.calendar.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.domain.Schedule;
import org.example.carrier.domain.category.domain.Category;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.carrier.domain.calendar.domain.QSchedule.schedule;
import static org.example.carrier.domain.category.domain.QCategory.category;

@RequiredArgsConstructor
@Repository
public class CustomScheduleRepositoryImpl implements CustomScheduleRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Schedule> findScheduleByDate(LocalDateTime startDate, LocalDateTime endDate, User user) {
        List<Category> categories = queryFactory
                .selectFrom(category)
                .where(category.active.isTrue()
                        .and(category.user.eq(user)))
                .fetch();

        return queryFactory
                .selectFrom(schedule)
                .where(schedule.startDate.between(startDate, endDate)
                        .or(schedule.endDate.between(startDate, endDate))
                        .and(schedule.user.eq(user).or(schedule.user.isNull()))
                        .and(schedule.category.in(categories)))
                .fetch();
    }

    @Override
    public List<Schedule> findScheduleByDateAndCategories(
            LocalDateTime startDate, LocalDateTime endDate, List<Category> categories, User user) {
        return queryFactory
                .selectFrom(schedule)
                .where(schedule.startDate.between(startDate, endDate)
                        .or(schedule.endDate.between(startDate, endDate))
                        .and(schedule.user.eq(user).or(schedule.user.isNull()))
                        .and(schedule.category.in(categories)))
                .fetch();
    }
}
