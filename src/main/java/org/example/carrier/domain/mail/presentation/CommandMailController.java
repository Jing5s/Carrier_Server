package org.example.carrier.domain.mail.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.mail.service.CommandMailService;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/mail")
@RestController
public class CommandMailController {
    private final CommandMailService commandMailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void batchSaveMail() {
        commandMailService.batchSaveMail(UserFacade.getCurrentUser());
    }
}
