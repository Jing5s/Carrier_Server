package org.example.carrier.domain.calendar.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.presentation.dto.request.FindCategoryRequest;
import org.example.carrier.domain.calendar.presentation.dto.response.GetSchedulesResponse;
import org.example.carrier.domain.calendar.service.QueryScheduleService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
public class QueryScheduleController {
    private final QueryScheduleService queryScheduleService;

    @GetMapping
    public List<GetSchedulesResponse> getSchedules(
            @Valid @ModelAttribute FindCategoryRequest request
    ) {
        return queryScheduleService.getSchedules(request, UserFacade.getCurrentUser());
    }
}
