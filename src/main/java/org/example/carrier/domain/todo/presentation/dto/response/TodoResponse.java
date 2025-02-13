package org.example.carrier.domain.todo.presentation.dto.response;

import org.example.carrier.domain.todo.domain.Todo;

public record TodoResponse(
    String title,
    Boolean isDone
) {
    public TodoResponse(Todo todo) {
        this(todo.getTitle(), todo.getIsDone());
    }
}
