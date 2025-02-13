package org.example.carrier.domain.todo.presentation;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.todo.presentation.dto.response.TodoResponse;
import org.example.carrier.domain.todo.service.QueryTodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/todo")
@RestController
public class QueryTodoController {
    private final QueryTodoService queryTodoService;

    @GetMapping
    public List<TodoResponse> getTodoList(
            @NotNull @RequestParam LocalDate date) {
        return queryTodoService.getTodoList(date);
    }
}
