package org.example.carrier.domain.mail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.domain.mail.domain.repository.MailRepository;
import org.example.carrier.domain.mail.exception.MailNotFoundException;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailResponse;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailSummaryResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.facade.GoogleOAuthFacade;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.config.properties.GptProperties;
import org.example.carrier.global.error.exception.GlobalException;
import org.example.carrier.global.feign.gmail.GmailAPIClient;
import org.example.carrier.global.feign.gmail.dto.request.ModifyLabelRequest;
import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;
import org.example.carrier.global.feign.gmail.dto.response.GmailListResponse;
import org.example.carrier.global.feign.gmail.dto.response.element.GmailHistory;
import org.example.carrier.global.feign.gpt.GptClient;
import org.example.carrier.global.feign.gpt.dto.request.GptBasicRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptImportMailRequest;
import org.example.carrier.global.feign.gpt.dto.request.GptMailSummaryRequest;
import org.example.carrier.global.feign.gpt.dto.response.GptBasicResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptImportMailResponse;
import org.example.carrier.global.feign.gpt.dto.response.GptMailSummaryResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@CustomService
@Slf4j
public class CommandMailService {
    private final MailRepository mailRepository;
    private final GmailAPIClient gmailAPIClient;
    private final GoogleOAuthFacade googleOAuthFacade;
    private final ObjectMapper objectMapper;
    private final GptClient gptClient;
    private final GptProperties gptProperties;

    private final List<String> exclusionLabels = Arrays.asList("SENT", "DRAFT", "TRASH");

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
            return new GetMailSummaryResponse(mail.getGmailId(), mail.getSummary());

        GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(gmailId, accessToken);

        GptMailSummaryResponse response = summaryMail(mail, gmailDetail);

        mail.updateSummary(response.summary());

        return new GetMailSummaryResponse(response.gmailId(), response.summary());
    }

    public void batchSaveMail(User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);

        GmailListResponse gmailList = gmailAPIClient.getGmailList(accessToken);

        gmailList.messages().forEach(message -> {
            GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(message.id(), accessToken);
            if (gmailDetail.labelIds().stream()
                    .anyMatch(exclusionLabels::contains)) {
                return;
            }
            log.info(toMail(gmailDetail, cUser).toString());
            mailRepository.save(toMail(gmailDetail, cUser));
        });
    }

    public void updateMail(User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);
        Long maxHistoryId = mailRepository.findMaxHistoryId(cUser);

        List<GmailHistory> histories = gmailAPIClient.getHistory(maxHistoryId, accessToken).history();
        if (histories == null) {
            return;
        }

        Set<String> updateMailId = histories.stream()
                .map(GmailHistory::getMailId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        updateMailId.forEach(mailId -> {
            try {
                GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(mailId, accessToken);
                if (gmailDetail.labelIds().stream()
                        .anyMatch(exclusionLabels::contains)) {
                    return;
                }

                Mail newGmail = toMail(gmailDetail, cUser);
                Optional<Mail> gmail = mailRepository.findByGmailId(mailId);

                if (gmail.isPresent()) {
                    gmail.get().update(newGmail);
                } else {
                    Mail mail = mailRepository.save(newGmail);

                    GptBasicRequest request = GptImportMailRequest.of(
                            objectMapper, mail.getGmailId(), mail.getSubject(), mail.getFrom(), mail.getTo(),
                            mail.getPreview(), mail.getDate(), mail.getIsRead(), mail.getLabels());

                    GptBasicResponse gptResponse = gptClient.getGptResponse("Bearer " + gptProperties.getToken(), request);
                    GptImportMailResponse response = (GptImportMailResponse) gptResponse.getResponse(objectMapper, GptImportMailResponse.class);

                    mail.updateIsImportant(response.important());
                    if (response.important()) {
                        String summary = summaryMail(mail, gmailDetail).summary();
                        mail.updateSummary(summary);
                    }
                }
            } catch (GlobalException ignored) {
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void readMail(String gmailId, User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);

        gmailAPIClient.modifyLabels(gmailId, new ModifyLabelRequest(), "Bearer " + accessToken);
    }

    private GptMailSummaryResponse summaryMail(Mail mail, GmailDetailResponse gmailDetail) throws JsonProcessingException {
        GptBasicRequest request = GptMailSummaryRequest.of(
                objectMapper, mail.getGmailId(), mail.getTitle(), gmailDetail.payload().getBody());

        GptBasicResponse gptResponse = gptClient.getGptResponse("Bearer " + gptProperties.getToken(), request);
        return (GptMailSummaryResponse) gptResponse.getResponse(objectMapper, GptMailSummaryResponse.class);
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
                gmail.getDate(),
                gmail.isRead(),
                gmail.historyId(),
                gmail.labelIds(),
                user
        );

    }
}
