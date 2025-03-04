package org.example.carrier.domain.todo.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.todo.presentation.dto.request.AddTodoRequest;
import org.example.carrier.domain.todo.service.CommandTodoService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/todos")
@RestController
public class CommandTodoController {
    private final CommandTodoService commandTodoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodo(
            @Valid @RequestBody AddTodoRequest request
    ) {
        commandTodoService.createTodo(request, UserFacade.getCurrentUser());
    }

    @PatchMapping("/change/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeDoneStatus(
            @PathVariable Long id
    ) {
        commandTodoService.changeDoneStatus(id, UserFacade.getCurrentUser());
    }
}
