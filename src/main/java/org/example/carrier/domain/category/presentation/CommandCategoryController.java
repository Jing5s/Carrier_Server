package org.example.carrier.domain.category.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.category.presentation.dto.request.AddCategoryRequest;
import org.example.carrier.domain.category.presentation.dto.request.UpdateCategoryRequest;
import org.example.carrier.domain.category.service.CommandCategoryService;
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
@RequestMapping("/categories")
@RestController
public class CommandCategoryController {
    private final CommandCategoryService commandCategoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(
            @Valid @RequestBody AddCategoryRequest request
    ) {
        commandCategoryService.createCategory(request, UserFacade.getCurrentUser());
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(
            @Valid @RequestBody UpdateCategoryRequest request
    ) {
        commandCategoryService.updateCategory(request, UserFacade.getCurrentUser());
    }

    @PatchMapping("/change/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeActiveStatus(
            @PathVariable Long id
    ) {
        commandCategoryService.changeActiveStatus(id, UserFacade.getCurrentUser());
    }
}
