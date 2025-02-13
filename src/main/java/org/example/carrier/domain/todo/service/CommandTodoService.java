package org.example.carrier.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.todo.domain.repository.TodoRepository;
import org.example.carrier.domain.todo.presentation.dto.request.AddTodoRequest;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommandTodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public void createTodo(AddTodoRequest request, User cUser) {
        todoRepository.save(request.toTodo(cUser));
    }
}
