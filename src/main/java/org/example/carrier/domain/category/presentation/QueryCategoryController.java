package org.example.carrier.domain.category.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.category.presentation.dto.response.CategoryResponse;
import org.example.carrier.domain.category.service.QueryCategoryService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class QueryCategoryController {
    private final QueryCategoryService queryCategoryService;

    @GetMapping
    public List<CategoryResponse> listCategory() {
        return queryCategoryService.getCategories(UserFacade.getCurrentUser());
    }
}
