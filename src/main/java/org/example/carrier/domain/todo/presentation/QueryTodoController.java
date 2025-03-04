package org.example.carrier.domain.todo.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.todo.presentation.dto.request.GetTodosRequest;
import org.example.carrier.domain.todo.presentation.dto.response.TodoResponse;
import org.example.carrier.domain.todo.service.QueryTodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/todos")
@RestController
public class QueryTodoController {
    private final QueryTodoService queryTodoService;

    @GetMapping
    public List<TodoResponse> getTodos(
            @Valid @ModelAttribute GetTodosRequest request) {
        return queryTodoService.getTodos(request.date());
    }
}
