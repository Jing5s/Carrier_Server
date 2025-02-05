package org.example.carrier.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.exception.UserNotFoundException;
import org.example.carrier.global.security.auth.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof AuthDetails) {
            return ((AuthDetails) principal).getUser();
        }

        throw UserNotFoundException.EXCEPTION;
    }
}
