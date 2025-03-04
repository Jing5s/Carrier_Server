package org.example.carrier.domain.todo.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.example.carrier.domain.todo.domain.Todo;
import org.example.carrier.domain.todo.domain.type.Priority;
import org.example.carrier.domain.user.domain.User;

import java.time.LocalDate;

public record AddTodoRequest(
        @NotEmpty(message = "title이 비어있습니다.")
        String title,

        @NotNull(message = "date가 비어있습니다.")
        LocalDate date,

        @NotNull(message = "isRepeat이 비어있습니다.")
        Boolean isRepeat,

        @NotNull(message = "priority가 비어있습니다.")
        Priority priority,

        @NotEmpty(message = "memo가 비어있습니다.")
        String memo,

        @NotEmpty(message = "location이 비어있습니다.")
        String location
) {
    public Todo toTodo(User user) {
        return new Todo(
                title, date, isRepeat,
                priority, memo, location, user
        );
    }
}
