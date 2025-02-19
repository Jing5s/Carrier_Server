package org.example.carrier.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.presentation.dto.response.UserProfileResponse;
import org.example.carrier.global.annotation.CustomService;

@RequiredArgsConstructor
@CustomService(readOnly = true)
public class QueryUserService {

    public UserProfileResponse getUserInfo(User cuser) {
        return new UserProfileResponse(cuser);
    }
}
