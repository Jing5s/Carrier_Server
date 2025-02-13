package org.example.carrier.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.todo.domain.repository.TodoRepository;
import org.example.carrier.domain.todo.presentation.dto.response.TodoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryTodoService {
    private final TodoRepository todoRepository;

    @Transactional(readOnly = true)
    public List<TodoResponse> getTodoList(LocalDate date) {
        return todoRepository.findAllByDate(date).stream()
                .map(TodoResponse::new)
                .toList();
    }
}
