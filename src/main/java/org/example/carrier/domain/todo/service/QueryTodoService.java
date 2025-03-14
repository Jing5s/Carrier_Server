package org.example.carrier.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.todo.domain.Todo;
import org.example.carrier.domain.todo.domain.repository.TodoRepository;
import org.example.carrier.domain.todo.presentation.dto.request.GetTodosRequest;
import org.example.carrier.domain.todo.presentation.dto.response.TodoResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

import java.util.List;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryTodoService {
    private final TodoRepository todoRepository;

    public List<TodoResponse> getTodos(GetTodosRequest request, User cUser) {
        List<Todo> todos = todoRepository.findScheduleByDate(
                request.startDate(), request.endDate(), cUser);

        return todos.stream()
                .map(TodoResponse::new)
                .toList();
    }
}
