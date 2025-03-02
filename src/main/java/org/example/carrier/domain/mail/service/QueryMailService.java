package org.example.carrier.domain.mail.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.mail.domain.repository.CustomMailRepository;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailsResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.facade.GoogleOAuthFacade;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.feign.gmail.GmailAPIClient;
import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;

import java.util.List;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryMailService {
    private final GmailAPIClient gmailAPIClient;
    private final GoogleOAuthFacade googleOAuthFacade;
    private final CustomMailRepository mailRepository;

    public List<GetMailsResponse> getGmailList(User cUser) {
        return mailRepository.findAllByUserOrderByDate(cUser).stream()
                .map(GetMailsResponse::of)
                .toList();
    }

    public GmailDetailResponse getGmailDetail(String id, User cUser) {
        String accessToken = googleOAuthFacade.getGoogleAccessToken(cUser);
        return gmailAPIClient.getGmailDetail(id, accessToken);
    }
}
