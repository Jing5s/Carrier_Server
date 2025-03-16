package org.example.carrier.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.facade.UserFacade;
import org.example.carrier.domain.user.presentation.dto.request.UpdateNotificationRequest;
import org.example.carrier.domain.user.presentation.dto.request.UpdatePictureRequest;
import org.example.carrier.domain.user.presentation.dto.request.UpdateProfileRequest;
import org.example.carrier.domain.user.service.CommandUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class CommandUserController {
    private final CommandUserService commandUserService;

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfile(
            @Valid @ModelAttribute UpdateProfileRequest request
    ) {
        commandUserService.updateProfile(request, UserFacade.getCurrentUser());
    }

    @PatchMapping("/picture")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePicture(
            @Valid @ModelAttribute UpdatePictureRequest request
    ) throws IOException {
        commandUserService.updatePicture(request, UserFacade.getCurrentUser());
    }

    @PatchMapping("/notification")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNotification(
            @Valid @RequestBody UpdateNotificationRequest request
    ) {
        commandUserService.updateNotification(request, UserFacade.getCurrentUser());
    }
}
