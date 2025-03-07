package org.example.carrier.domain.mail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.domain.mail.domain.repository.CustomMailRepository;
import org.example.carrier.domain.mail.domain.repository.MailRepository;
import org.example.carrier.domain.mail.exception.MailNotFoundException;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailResponse;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailSummaryResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.facade.GoogleOAuthFacade;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.config.properties.GptProperties;
import org.example.carrier.global.feign.gmail.GmailAPIClient;
import org.example.carrier.global.feign.gmail.dto.request.ModifyLabelRequest;
import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;
import org.example.carrier.global.feign.gmail.dto.response.GmailHistoryResponse;
import org.example.carrier.global.feign.gmail.dto.response.GmailListResponse;
import org.example.carrier.global.feign.gmail.dto.response.element.GmailHistory;
import org.example.carrier.global.feign.gpt.GptClient;
import org.example.carrier.global.feign.gpt.dto.request.GptBasicRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptImportMailRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptMailSummaryRequest;
import org.example.carrier.global.feign.gpt.dto.response.GptBasicResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptImportMailResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptMailSummaryResponse;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@CustomService
@Slf4j
public class CommandMailService {
    private final MailRepository mailRepository;
    private final CustomMailRepository customMailRepository;
    private final GmailAPIClient gmailAPIClient;
    private final GoogleOAuthFacade googleOAuthFacade;
    private final ObjectMapper objectMapper;
    private final GptClient gptClient;
    private final GptProperties gptProperties;
    private final QueryMailService queryMailService;

    public GetMailResponse getGmailDetail(String gmailId, User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);

        Mail mail = mailRepository.findByGmailId(gmailId)
                .orElseThrow(() -> MailNotFoundException.EXCEPTION);
        GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(gmailId, accessToken);

        if (!mail.getHistoryId().equals(gmailDetail.historyId())) {
            mail.update(toMail(gmailDetail, cUser));
        }

        return GetMailResponse.of(mail, gmailDetail.payload());
    }

    public GetMailSummaryResponse getMailSummary(String gmailId, User cUser) throws JsonProcessingException {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);
        Mail mail = mailRepository.findByGmailId(gmailId)
                .orElseThrow(() -> MailNotFoundException.EXCEPTION);

        if (mail.getSummary() != null)
            return new GetMailSummaryResponse(mail.getSummary());

        GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(gmailId, accessToken);

        GptBasicRequest request = GptMailSummaryRequest.of(
                objectMapper, mail.getGmailId(), mail.getTitle(), gmailDetail.payload().getBody());

        GptBasicResponse gptResponse = gptClient.getGptResponse("Bearer " + gptProperties.getToken(), request);
        GptMailSummaryResponse response = (GptMailSummaryResponse) gptResponse.getResponse(objectMapper, GptMailSummaryResponse.class);

        mail.updateSummary(response.summary());

        return new GetMailSummaryResponse(response.summary());
    }

    public void batchSaveMail(User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);

        GmailListResponse gmailList = gmailAPIClient.getGmailList(accessToken);

        gmailList.messages().forEach(message -> {
            GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(message.id(), accessToken);

            mailRepository.save(toMail(gmailDetail, cUser));
        });
    }

    public void updateMail(User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);
        Long maxHistoryId = customMailRepository.findMaxHistoryId(cUser);

        Set<String> updateMailId = Optional.ofNullable(gmailAPIClient.getHistory(maxHistoryId, accessToken))
                .map(GmailHistoryResponse::history)
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.groupingBy(GmailHistory::getMailId))
                .keySet();

        updateMailId.forEach(mailId -> {
            GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(mailId, accessToken);
            Mail newGmail = toMail(gmailDetail, cUser);

            Optional<Mail> gmail = mailRepository.findByGmailId(mailId);
            try {
                if (gmail.isPresent()) {
                    gmail.get().update(newGmail);
                } else {
                    Mail mail = mailRepository.save(newGmail);

                    GptBasicRequest request = GptImportMailRequest.of(
                            objectMapper, mail.getGmailId(), mail.getSubject(), mail.getFrom(), mail.getTo(),
                            mail.getPreview(), mail.getDate(), mail.getIsRead(), mail.getLabels());

                    GptBasicResponse gptResponse = gptClient.getGptResponse("Bearer " + gptProperties.getToken(), request);
                    GptImportMailResponse response = (GptImportMailResponse) gptResponse.getResponse(objectMapper, GptMailSummaryResponse.class);

                    mail.updateIsImportant(response.important());
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void readMail(String gmailId, User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);

        gmailAPIClient.modifyLabels(gmailId, new ModifyLabelRequest(), "Bearer " + accessToken);
    }

    private static Mail toMail(GmailDetailResponse gmail, User user) {
        String from = gmail.payload().getFrom();
        int index = from.indexOf(" <");
        String title = (index != -1) ? from.substring(0, index) : from;

        return new Mail(
                gmail.id(), gmail.threadId(), title,
                from,
                gmail.payload().getTo(),
                gmail.payload().getSubject(),
                gmail.snippet(),
                gmail.payload().getDate(),
                gmail.isRead(),
                gmail.historyId(),
                gmail.labelIds(),
                user
        );

    }
}
