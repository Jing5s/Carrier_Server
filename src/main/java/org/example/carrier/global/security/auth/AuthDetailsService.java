package org.example.carrier.global.security.auth;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.repository.UserRepository;
import org.example.carrier.domain.user.exception.UserNotFoundException;
import org.example.carrier.domain.user.facade.UserFacade;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {
    private final UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AuthDetails(
                userFacade.getUserByEmail(email)
        );
    }
}
