package org.example.carrier.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.facade.UserFacade;
import org.example.carrier.domain.user.presentation.dto.response.UserProfileResponse;
import org.example.carrier.domain.user.service.GetUserProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final GetUserProfileService getUserProfileService;

    @GetMapping
    public UserProfileResponse getUserProfile() {
        return getUserProfileService.execute(UserFacade.getCurrentUser());
    }
}
