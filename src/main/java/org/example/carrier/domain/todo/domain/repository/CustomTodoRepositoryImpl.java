package org.example.carrier.domain.todo.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.todo.domain.Todo;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static org.example.carrier.domain.todo.domain.QTodo.todo;

@RequiredArgsConstructor
@Repository
public class CustomTodoRepositoryImpl implements CustomTodoRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Todo> findScheduleByDate(LocalDate startDate, LocalDate endDate, User user) {
        return queryFactory
                .selectFrom(todo)
                .where(todo.date.between(startDate, endDate)
                        .and(todo.user.eq(user)))
                .fetch();
    }
}
