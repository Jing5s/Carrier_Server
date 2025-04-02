package org.example.carrier.domain.meet.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.meet.presentation.dto.response.GetMeetsResponse;
import org.example.carrier.domain.meet.presentation.dto.response.MeetSummaryResponse;
import org.example.carrier.domain.meet.service.QueryMeetService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public List<GetMeetsResponse> getMeets() {
        return queryMeetService.getMeets(UserFacade.getCurrentUser());
    }

    @GetMapping("/audio/{id}")
    public ResponseEntity<Resource> getMeetAudio(
            @PathVariable Long id
    ) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf("video/webm")) // 반드시 명시
                .body(queryMeetService.getAudioFile(id, UserFacade.getCurrentUser()));
    }
}
