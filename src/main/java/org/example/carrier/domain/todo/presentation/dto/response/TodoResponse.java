package org.example.carrier.domain.todo.presentation.dto.response;

import org.example.carrier.domain.todo.domain.Todo;
import org.example.carrier.domain.todo.domain.type.Priority;

import java.time.LocalDate;

public record TodoResponse(
        Long id,
        String title,
        Boolean isDone,
        String memo,
        Priority priority,
        LocalDate date
) {
    public TodoResponse(Todo todo) {
        this(todo.getId(), todo.getTitle(), todo.getIsDone(), todo.getMemo(), todo.getPriority(), todo.getDate());
    }
}
