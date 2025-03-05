package org.example.carrier.domain.mail.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.mail.presentation.dto.response.GetMailResponse;
import org.example.carrier.domain.mail.service.CommandMailService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/mails")
@RestController
public class CommandMailController {
    private final CommandMailService commandMailService;

    @GetMapping("/{gmail-id}")
    public GetMailResponse getGmailDetail(
            @PathVariable("gmail-id") String gmailId
    ) {
        return commandMailService.getGmailDetail(gmailId, UserFacade.getCurrentUser());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void batchSaveMail() {
        commandMailService.batchSaveMail(UserFacade.getCurrentUser());
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMail() {
        commandMailService.updateMail(UserFacade.getCurrentUser());
    }

    @PatchMapping("/read/{gmail-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void readMail(
            @PathVariable("gmail-id") String gmailId
    ) {
        commandMailService.readMail(gmailId, UserFacade.getCurrentUser());
    }
}
