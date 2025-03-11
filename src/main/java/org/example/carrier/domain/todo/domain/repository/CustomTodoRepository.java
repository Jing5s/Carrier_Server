package org.example.carrier.domain.todo.domain.repository;

import org.example.carrier.domain.todo.domain.Todo;
import org.example.carrier.domain.user.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface CustomTodoRepository {
    List<Todo> findScheduleByDate(LocalDate startDate, LocalDate endDate, User user);
}
