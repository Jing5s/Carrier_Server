package org.example.carrier.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.todo.domain.repository.TodoRepository;
import org.example.carrier.domain.todo.presentation.dto.response.TodoResponse;
import org.example.carrier.global.annotation.CustomService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryTodoService {
    private final TodoRepository todoRepository;

    public List<TodoResponse> getTodoList(LocalDate date) {
        return todoRepository.findAllByDate(date).stream()
                .map(TodoResponse::new)
                .toList();
    }
}
