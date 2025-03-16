package org.example.carrier.global.utils;

import lombok.RequiredArgsConstructor;
import org.example.carrier.global.config.properties.NextCloudProperties;
import org.example.carrier.global.feign.nextcloud.NextcloudShareClient;
import org.example.carrier.global.feign.nextcloud.NextcloudUploadClient;
import org.example.carrier.global.feign.nextcloud.dto.response.ShareUrlResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class NextCloudService {
    private final NextCloudProperties nextCloudProperties;
    private final NextcloudUploadClient nextcloudUploadClient;
    private final NextcloudShareClient nextcloudShareClient;

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        byte[] fileData = file.getBytes();

        String authHeader = "Basic " + Base64.getEncoder()
                .encodeToString((nextCloudProperties.getUsername() + ":" + nextCloudProperties.getPassword())
                .getBytes(StandardCharsets.UTF_8));

        nextcloudUploadClient.uploadFile(fileName, fileData, authHeader);

        ShareUrlResponse shareUrlResponse = nextcloudShareClient.createPublicShare(
                authHeader, "true", "application/json",
                "/" + fileName, 3, 1);

        return shareUrlResponse.getUrl();
    }
}
