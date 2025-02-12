package org.example.carrier.domain.calendar.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.presentation.dto.request.FindCategoryRequest;
import org.example.carrier.domain.calendar.presentation.dto.response.ScheduleResponse;
import org.example.carrier.domain.calendar.service.QueryScheduleService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
public class QueryScheduleController {
    private final QueryScheduleService queryScheduleService;

    @PostMapping
    public List<ScheduleResponse> listSchedule(@Valid @RequestBody FindCategoryRequest request) {
        return queryScheduleService.getSchedule(request, UserFacade.getCurrentUser());
    }
}
