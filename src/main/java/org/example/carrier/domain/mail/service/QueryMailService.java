package org.example.carrier.domain.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.carrier.domain.mail.domain.repository.MailRepository;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.facade.GoogleOAuthFacade;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.feign.gmail.GmailAPIClient;
import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;
import org.example.carrier.global.feign.gmail.dto.response.GmailListResponse;
import org.example.carrier.global.feign.gmail.dto.response.element.GmailListDetail;

@RequiredArgsConstructor
@CustomService(readOnly = true)
@Slf4j
public class QueryMailService {
    private final GmailAPIClient gmailAPIClient;
    private final GoogleOAuthFacade googleOAuthFacade;
    private final MailRepository mailRepository;

    public GmailListResponse getGmailList(User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);
log.info(accessToken);
        GmailListResponse gmailList = gmailAPIClient.getGmailList(accessToken);
        return gmailList;
    }

    public GmailDetailResponse getGmailDetail(String id, User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);
        return gmailAPIClient.getGmailDetail(id, accessToken);
    }
}
