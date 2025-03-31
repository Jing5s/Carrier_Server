package org.example.carrier.domain.meet.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.meet.presentation.dto.response.MeetSummaryResponse;
import org.example.carrier.domain.meet.service.QueryMeetService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/meets")
@RestController
public class QueryMeetController {
    private final QueryMeetService queryMeetService;

    @GetMapping("/{id}")
    public MeetSummaryResponse getMeet(
            @PathVariable Long id
    ) {
        return queryMeetService.getMeet(id, UserFacade.getCurrentUser());
    }
}
