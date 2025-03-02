package org.example.carrier.domain.mail.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.mail.domain.repository.CustomMailRepository;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailsResponse;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.global.annotation.CustomService;

import java.util.List;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryMailService {
    private final CustomMailRepository mailRepository;

    public List<GetMailsResponse> getGmailList(User cUser) {
        return mailRepository.findAllByUserOrderByDate(cUser).stream()
                .map(GetMailsResponse::of)
                .toList();
    }
}
