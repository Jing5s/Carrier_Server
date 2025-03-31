package org.example.carrier.domain.meet.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.meet.presentation.dto.request.MeetSummaryRequest;
import org.example.carrier.domain.meet.presentation.dto.response.MeetSummaryResponse;
import org.example.carrier.domain.meet.service.CommandMeetService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/meets")
@RestController
public class CommendMeetController {
    private final CommandMeetService commandMeetService;

    @PostMapping
    public MeetSummaryResponse meetSummary(
            @Valid @ModelAttribute MeetSummaryRequest request
    ) throws JsonProcessingException {
        return commandMeetService.meetSummary(request, UserFacade.getCurrentUser());
    }
}
