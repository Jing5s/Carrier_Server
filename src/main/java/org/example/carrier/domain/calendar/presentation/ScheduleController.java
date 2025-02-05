package org.example.carrier.domain.calendar.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.presentation.dto.request.AddScheduleRequest;
import org.example.carrier.domain.calendar.presentation.dto.request.FindCategoryRequest;
import org.example.carrier.domain.calendar.presentation.dto.response.ScheduleResponse;
import org.example.carrier.domain.calendar.service.AddScheduleService;
import org.example.carrier.domain.calendar.service.ListScheduleService;
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
@RequestMapping("/calendar")
@RestController
public class ScheduleController {
    private final ListScheduleService listScheduleService;
    private final AddScheduleService addScheduleService;

    @GetMapping
    public List<ScheduleResponse> listSchedule(@Valid @RequestBody FindCategoryRequest request) {
        return listScheduleService.execute(request, UserFacade.getCurrentUser());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSchedule(@Valid @RequestBody AddScheduleRequest request) {
        addScheduleService.execute(request, UserFacade.getCurrentUser());
    }
}
