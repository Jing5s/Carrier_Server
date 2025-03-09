package org.example.carrier.domain.user.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.facade.UserFacade;
import org.example.carrier.domain.user.presentation.dto.response.UserProfileResponse;
import org.example.carrier.domain.user.service.QueryUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class QueryUserController {
    private final QueryUserService queryUserService;

    @GetMapping
    public UserProfileResponse getUserProfile() {
        return queryUserService.getUserInfo(UserFacade.getCurrentUser());
    }

    @GetMapping("/tips")
    public Object getTodaySummary() throws JsonProcessingException {
        return queryUserService.getTodaySummary(UserFacade.getCurrentUser());
    }
}
