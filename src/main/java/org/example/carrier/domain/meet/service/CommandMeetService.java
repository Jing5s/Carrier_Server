package org.example.carrier.domain.meet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.meet.domain.Meet;
import org.example.carrier.domain.meet.domain.repository.MeetRepository;
import org.example.carrier.domain.meet.exception.MeetNotFoundException;
import org.example.carrier.domain.meet.presentation.dto.request.MeetSummaryRequest;
import org.example.carrier.domain.meet.presentation.dto.response.MeetSummaryResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.config.properties.GptProperties;
import org.example.carrier.global.feign.gpt.GptClient;
import org.example.carrier.global.feign.gpt.dto.request.GptBasicRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptMeetSummaryRequest;
import org.example.carrier.global.feign.gpt.dto.response.GptBasicResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptMeetSummeryResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptMeetTextResponse;

@RequiredArgsConstructor
@CustomService
public class CommandMeetService {
    private final MeetRepository meetRepository;
    private final GptClient gptClient;
    private final GptProperties gptProperties;
    private final ObjectMapper objectMapper;

    public MeetSummaryResponse meetSummary(MeetSummaryRequest request, User cUser) throws JsonProcessingException {
        GptMeetTextResponse meetText = gptClient.getMeetText(
                "Bearer " + gptProperties.getToken(),
                request.file(), "gpt-4o-transcribe"
        );

        GptBasicRequest gptBasicRequest
                = GptBasicRequest.meetSummary(objectMapper, new GptMeetSummaryRequest(meetText.text()));

        GptBasicResponse gptResponse
                = gptClient.getGptResponse("Bearer " + gptProperties.getToken(), gptBasicRequest);

        GptMeetSummeryResponse result
                = (GptMeetSummeryResponse) gptResponse.getResponse(objectMapper, GptMeetSummeryResponse.class);

        Meet meet = meetRepository.save(
                new Meet(
                        result.title(),
                        meetText.text(),
                        result.text(),
                        cUser
                )
        );

        return MeetSummaryResponse.of(meet);
    }

    public void deleteMeet(Long id, User cUser) {
        Meet meet = meetRepository.findByIdAndUser(id, cUser)
                .orElseThrow(() -> MeetNotFoundException.EXCEPTION);

        meetRepository.delete(meet);
    }
}
