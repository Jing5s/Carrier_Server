package org.example.carrier.domain.todo.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.todo.domain.Todo;
import org.example.carrier.domain.todo.domain.repository.CustomTodoRepository;
import org.example.carrier.domain.todo.presentation.dto.request.GetTodosRequest;
import org.example.carrier.domain.todo.presentation.dto.response.TodoResponse;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/todos")
@RestController
public class QueryTodoController {
    private final CustomTodoRepository queryTodoService;

    @GetMapping
    public List<TodoResponse> getTodos(
            @Valid @ModelAttribute GetTodosRequest request) {
        List<Todo> todos = queryTodoService.findScheduleByDate(
                request.startDate(), request.endDate(), UserFacade.getCurrentUser());

        return todos.stream()
                .map(TodoResponse::new)
                .toList();
    }
}
