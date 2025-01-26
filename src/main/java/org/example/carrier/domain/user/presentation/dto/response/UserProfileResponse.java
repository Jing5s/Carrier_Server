package org.example.carrier.domain.user.presentation.dto.response;

import org.example.carrier.domain.user.domain.User;

public record UserProfileResponse(
        String email,
        String nickname,
        String picture
) {
    public UserProfileResponse(User user) {
        this(
                user.getEmail(),
                user.getNickname(),
                user.getPicture()
        );
    }
}
