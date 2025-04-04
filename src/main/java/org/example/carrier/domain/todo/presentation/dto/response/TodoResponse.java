package org.example.carrier.domain.todo.presentation.dto.response;

import org.example.carrier.domain.todo.domain.Todo;

import java.time.LocalDate;

public record TodoResponse(
        Long id,
        String title,
        Boolean isDone,
        LocalDate date
) {
    public TodoResponse(Todo todo) {
        this(todo.getId(), todo.getTitle(), todo.getIsDone(), todo.getDate());
    }
}
