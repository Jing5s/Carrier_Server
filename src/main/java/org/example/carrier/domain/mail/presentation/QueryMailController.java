package org.example.carrier.domain.mail.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailsResponse;
import org.example.carrier.domain.mail.service.QueryMailService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.example.carrier.global.feign.gmail.dto.response.GmailDetailResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/mail")
@RestController
public class QueryMailController {
    private final QueryMailService queryMailService;

    @GetMapping
    public List<GetMailsResponse> getGmailList() {
        return queryMailService.getGmailList(UserFacade.getCurrentUser());
    }

    @GetMapping("/{id}")
    public GmailDetailResponse getGmailDetail(@PathVariable String id) {
        return queryMailService.getGmailDetail(id, UserFacade.getCurrentUser());
    }
}
