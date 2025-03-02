package org.example.carrier.domain.mail.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.domain.mail.domain.repository.MailRepository;
import org.example.carrier.domain.mail.exception.MailNotFoundException;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.facade.GoogleOAuthFacade;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.feign.gmail.GmailAPIClient;
import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;
import org.example.carrier.global.feign.gmail.dto.response.GmailListResponse;

@RequiredArgsConstructor
@CustomService
public class CommandMailService {
    private final MailRepository mailRepository;
    private final GmailAPIClient gmailAPIClient;
    private final GoogleOAuthFacade googleOAuthFacade;

    public void batchSaveMail(User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);

        GmailListResponse gmailList = gmailAPIClient.getGmailList(accessToken);

        gmailList.messages().forEach(message -> {
            GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(message.id(), accessToken);

            mailRepository.save(toMail(gmailDetail, cUser));
        });
    }

    public GetMailResponse getGmailDetail(String gmailId, User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);

        Mail mail = mailRepository.findByGmailId(gmailId)
                .orElseThrow(() -> MailNotFoundException.EXCEPTION);
        GmailDetailResponse gmailDetail = gmailAPIClient.getGmailDetail(gmailId, accessToken);

        if (!mail.getHistoryId().equals(gmailDetail.historyId())) {
            mail.update(toMail(gmailDetail, cUser));
        }

        return GetMailResponse.of(mail, gmailDetail.payload().parts());
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
