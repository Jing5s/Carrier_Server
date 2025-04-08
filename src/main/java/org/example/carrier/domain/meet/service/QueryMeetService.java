package org.example.carrier.domain.meet.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.meet.domain.Meet;
import org.example.carrier.domain.meet.domain.repository.MeetRepository;
import org.example.carrier.domain.meet.exception.MeetNotFoundException;
import org.example.carrier.domain.meet.presentation.dto.response.GetMeetsResponse;
import org.example.carrier.domain.meet.presentation.dto.response.MeetSummaryResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.utils.NextCloudService;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryMeetService {
    private final MeetRepository meetRepository;
    private final NextCloudService nextCloudService;

    public MeetSummaryResponse getMeet(Long id, User cUser) {
        Meet meet = meetRepository.findByIdAndUser(id, cUser)
                .orElseThrow(() -> MeetNotFoundException.EXCEPTION);

        return MeetSummaryResponse.of(meet);
    }

    public List<GetMeetsResponse> getMeets(User cUser) {
        return meetRepository.findAllMeetsDesc(cUser).stream()
                .map(GetMeetsResponse::of)
                .collect(Collectors.toList());
    }

    public Resource getAudioFile(Long id, User cUser) {
        Meet meet = meetRepository.findByIdAndUser(id, cUser)
                .orElseThrow(() -> MeetNotFoundException.EXCEPTION);

        return nextCloudService.getFile(meet.getFileName());
    }
}
