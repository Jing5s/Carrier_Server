package org.example.carrier.domain.calendar.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.presentation.dto.request.AddCategoryRequest;
import org.example.carrier.domain.calendar.service.CommandCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CommandCategoryController {
    private final CommandCategoryService commandCategoryService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@Valid @RequestBody AddCategoryRequest request) {
        commandCategoryService.createCategory(request);
    }
}
