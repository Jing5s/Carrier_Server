package org.example.carrier.domain.calendar.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.presentation.dto.request.AddCategoryRequest;
import org.example.carrier.domain.calendar.presentation.dto.response.CategoryResponse;
import org.example.carrier.domain.calendar.service.AddCategoryService;
import org.example.carrier.domain.calendar.service.ListCategoryService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {
    private final ListCategoryService listCategoryService;
    private final AddCategoryService addCategoryService;

    @GetMapping
    public List<CategoryResponse> listCategory() {
        return listCategoryService.execute(UserFacade.getCurrentUser());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCategory(@Valid @RequestBody AddCategoryRequest request) {
        addCategoryService.execute(request);
    }
}
