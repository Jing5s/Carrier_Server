package org.example.carrier.domain.mail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.domain.mail.domain.repository.MailRepository;
import org.example.carrier.domain.mail.exception.MailNotFoundException;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailsResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.facade.GoogleOAuthFacade;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.config.properties.GptProperties;
import org.example.carrier.global.feign.gmail.GmailAPIClient;
import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;
import org.example.carrier.global.feign.gpt.GptClient;
import org.example.carrier.global.feign.gpt.dto.request.GptBasicRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptMailScheduleRequest;
import org.example.carrier.global.feign.gpt.dto.response.GptBasicResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptMailScheduleResponse;

import java.util.List;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryMailService {
    private final MailRepository mailRepository;
    private final GmailAPIClient gmailAPIClient;
    private final GoogleOAuthFacade googleOAuthFacade;
    private final ObjectMapper objectMapper;
    private final GptClient gptClient;
    private final GptProperties gptProperties;

    public List<GetMailsResponse> getMails(User cUser) {
        return mailRepository.findAllByUserOrderByDate(cUser).stream()
                .map(GetMailsResponse::of)
                .toList();
    }

    public GptMailScheduleResponse getMailSchedule(String gmailId, User cUser) throws JsonProcessingException {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);
        Mail mail = mailRepository.findByGmailId(gmailId)
                .orElseThrow(() -> MailNotFoundException.EXCEPTION);

        GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(gmailId, accessToken);

        GptBasicRequest request = GptMailScheduleRequest.of(
                objectMapper, mail.getGmailId(), mail.getTitle(), gmailDetail.payload().getBody());

        GptBasicResponse gptResponse = gptClient.getGptResponse("Bearer " + gptProperties.getToken(), request);

        return (GptMailScheduleResponse) gptResponse.getResponse(objectMapper, GptMailScheduleResponse.class);
    }
}
