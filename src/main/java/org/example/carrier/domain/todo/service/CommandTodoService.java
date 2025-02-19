package org.example.carrier.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.todo.domain.Todo;
import org.example.carrier.domain.todo.domain.repository.TodoRepository;
import org.example.carrier.domain.todo.exception.TodoNotFoundException;
import org.example.carrier.domain.todo.presentation.dto.request.AddTodoRequest;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

@RequiredArgsConstructor
@CustomService
public class CommandTodoService {
    private final TodoRepository todoRepository;

    public void createTodo(AddTodoRequest request, User cUser) {
        todoRepository.save(request.toTodo(cUser));
    }

    public void changeDoneStatus(Long id, User cUser) {
        Todo todo = todoRepository.findByIdAndUser(id, cUser)
                .orElseThrow(() -> TodoNotFoundException.EXCEPTION);

        todo.changeDoneStatus();
    }
}
