package org.example.carrier.domain.category.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.category.presentation.dto.response.GetCategoriesResponse;
import org.example.carrier.domain.category.service.QueryCategoryService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class QueryCategoryController {
    private final QueryCategoryService queryCategoryService;

    @GetMapping
    public List<GetCategoriesResponse> getCategories() {
        return queryCategoryService.getCategories(UserFacade.getCurrentUser());
    }
}
