package org.example.carrier.domain.user.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.carrier.domain.user.domain.User;

import java.time.LocalTime;

public record UserProfileResponse(
        Boolean isSurvey,
        String email,
        String nickname,
        String picture,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime notificationTime
) {
    public UserProfileResponse(User user, Boolean isSurvey) {
        this(
                isSurvey,
                user.getEmail(),
                user.getNickname(),
                user.getPicture(),
                user.getNotificationTime()
        );
    }
}
