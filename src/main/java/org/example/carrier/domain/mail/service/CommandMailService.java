package org.example.carrier.domain.mail.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.domain.mail.domain.repository.CustomMailRepository;
import org.example.carrier.domain.mail.domain.repository.MailRepository;
import org.example.carrier.domain.mail.exception.MailNotFoundException;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.facade.GoogleOAuthFacade;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.feign.gmail.GmailAPIClient;
import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;
import org.example.carrier.global.feign.gmail.dto.response.GmailListResponse;
import org.example.carrier.global.feign.gmail.dto.response.element.GmailHistory;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@CustomService
public class CommandMailService {
    private final MailRepository mailRepository;
    private final CustomMailRepository customMailRepository;
    private final GmailAPIClient gmailAPIClient;
    private final GoogleOAuthFacade googleOAuthFacade;

    public GetMailResponse getGmailDetail(String gmailId, User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);

        Mail mail = mailRepository.findByGmailId(gmailId)
                .orElseThrow(() -> MailNotFoundException.EXCEPTION);
        GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(gmailId, accessToken);

        if (!mail.getHistoryId().equals(gmailDetail.historyId())) {
            mail.update(toMail(gmailDetail, cUser));
        }

        return GetMailResponse.of(mail, gmailDetail.payload().body(), gmailDetail.payload().parts());
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

        Set<String> updateMailId = gmailAPIClient.getHistory(maxHistoryId, accessToken).history().stream()
                .collect(Collectors.groupingBy(GmailHistory::getMailId))
                .keySet();

        updateMailId.forEach(mailId -> {
            GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(mailId, accessToken);
            Mail newGmail = toMail(gmailDetail, cUser);

            Optional<Mail> gmail = mailRepository.findByGmailId(mailId);
            if (gmail.isPresent()) {
                gmail.get().update(newGmail);
            } else {
                mailRepository.save(newGmail);
            }
        });
    }

    private static Mail toMail(GmailDetailResponse gmail, User user) {
        return new Mail(
                gmail.id(), gmail.threadId(), gmail.snippet(),
                gmail.payload().getFrom(),
                gmail.payload().getTo(),
                gmail.payload().getSubject(),
                gmail.payload().getDate(),
                gmail.isRead(),
                gmail.historyId(),
                gmail.labelIds(),
                user
        );

    }
}
