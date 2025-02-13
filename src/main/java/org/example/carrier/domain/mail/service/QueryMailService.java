package org.example.carrier.domain.mail.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.facade.GoogleOAuthFacade;
import org.example.carrier.global.feign.gmail.GmailAPIClient;
import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;
import org.example.carrier.global.feign.gmail.dto.response.GmailListResponse;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryMailService {
    private final GmailAPIClient gmailAPIClient;
    private final GoogleOAuthFacade googleOAuthFacade;

    public GmailListResponse getGmailList(User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);
        return gmailAPIClient.getGmailList(accessToken);
    }

    public GmailDetailResponse getGmailDetail(String id, User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);
        return gmailAPIClient.getGmailDetail(id, accessToken);
    }
}
