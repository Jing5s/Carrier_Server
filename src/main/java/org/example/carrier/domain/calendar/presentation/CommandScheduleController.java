package org.example.carrier.domain.calendar.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.calendar.presentation.dto.request.AddScheduleRequest;
import org.example.carrier.domain.calendar.presentation.dto.request.UpdateScheduleRequest;
import org.example.carrier.domain.calendar.service.CommandScheduleService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
public class CommandScheduleController {
    private final CommandScheduleService commandScheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSchedule(
            @Valid @RequestBody AddScheduleRequest request
    ) {
        commandScheduleService.createSchedule(request, UserFacade.getCurrentUser());
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSchedule(
            @Valid @RequestBody UpdateScheduleRequest request
    ) {
        commandScheduleService.updateSchedule(request, UserFacade.getCurrentUser());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(
            @PathVariable Long id
    ) {
        commandScheduleService.deleteSchedule(id, UserFacade.getCurrentUser());
    }
}
