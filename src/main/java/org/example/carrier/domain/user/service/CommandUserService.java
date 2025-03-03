package org.example.carrier.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.domain.repository.UserRepository;
import org.example.carrier.domain.user.presentation.dto.request.UpdateProfileRequest;
import org.example.carrier.global.annotation.CustomService;

@RequiredArgsConstructor
@CustomService
public class CommandUserService {
    private final UserRepository userRepository;

    public void updateProfile(UpdateProfileRequest request, User cUser) {
        User updateUser = cUser.update(request.nickname());
        userRepository.save(updateUser);
    }
}
