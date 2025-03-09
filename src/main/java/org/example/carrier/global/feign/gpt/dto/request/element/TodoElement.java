package org.example.carrier.global.feign.gpt.dto.request.element;

import org.example.carrier.domain.todo.domain.Todo;

public record TodoElement(
        Long id,
        String title,
        Boolean isDone
) {
    public static TodoElement from(Todo todo) {
        return new TodoElement(
                todo.getId(),
                todo.getTitle(),
                todo.getIsDone()
        );
    }
}
