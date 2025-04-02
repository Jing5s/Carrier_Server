package org.example.carrier.domain.user.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.user.domain.User;
import org.example.carrier.domain.user.domain.repository.UserRepository;
import org.example.carrier.domain.user.presentation.dto.request.UpdateNotificationRequest;
import org.example.carrier.domain.user.presentation.dto.request.UpdatePictureRequest;
import org.example.carrier.domain.user.presentation.dto.request.UpdateProfileRequest;
import org.example.carrier.global.annotation.CustomService;
import org.example.carrier.global.utils.NextCloudService;

import java.io.IOException;

@RequiredArgsConstructor
@CustomService
public class CommandUserService {
    private final UserRepository userRepository;
    private final NextCloudService nextCloudService;

    public void updateProfile(@Valid UpdateProfileRequest request, User cUser) {
        User updateUser = cUser.update(request.nickname());
        userRepository.save(updateUser);
    }

    public void updatePicture(@Valid UpdatePictureRequest request, User cUser) throws IOException {
        String imageUrl = nextCloudService.uploadFile(request.picture(), cUser.getId()).uploadUrl();
        User updateUser = cUser.updatePicture(imageUrl);

        userRepository.save(updateUser);
    }

    public void updateNotification(@Valid UpdateNotificationRequest request, User cUser) {
        User updateUser = cUser.updateNotificationTime(request.time());
        userRepository.save(updateUser);
    }

    public void deleteUser(User cUser) {
        userRepository.delete(cUser);
    }
}
