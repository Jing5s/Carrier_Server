package org.example.carrier.domain.meet.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.meet.domain.Meet;
import org.example.carrier.domain.meet.domain.repository.MeetRepository;
import org.example.carrier.domain.meet.exception.MeetNotFoundException;
import org.example.carrier.domain.meet.presentation.dto.response.MeetSummaryResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryMeetService {
    private final MeetRepository meetRepository;

    public MeetSummaryResponse getMeet(Long id, User cUser) {
        Meet meet = meetRepository.findByIdAndUser(id, cUser)
                .orElseThrow(() -> MeetNotFoundException.EXCEPTION);

        return MeetSummaryResponse.of(meet);
    }
}
