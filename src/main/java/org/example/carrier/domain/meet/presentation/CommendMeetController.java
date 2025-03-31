package org.example.carrier.domain.meet.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.meet.presentation.dto.request.MeetSummaryRequest;
import org.example.carrier.domain.meet.presentation.dto.response.MeetSummaryResponse;
import org.example.carrier.domain.meet.service.CommandMeetService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/meets")
@RestController
public class CommendMeetController {
    private final CommandMeetService commandMeetService;

    @PostMapping
    public MeetSummaryResponse meetSummary(
            @Valid @ModelAttribute MeetSummaryRequest request
    ) throws IOException {
        return commandMeetService.meetSummary(request, UserFacade.getCurrentUser());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMeet(
            @PathVariable Long id
    ) {
        commandMeetService.deleteMeet(id, UserFacade.getCurrentUser());
    }
}
