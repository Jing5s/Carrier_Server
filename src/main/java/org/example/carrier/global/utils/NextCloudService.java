package org.example.carrier.global.utils;

import lombok.RequiredArgsConstructor;
import org.example.carrier.global.config.properties.NextCloudProperties;
import org.example.carrier.global.feign.nextcloud.NextcloudFilesClient;
import org.example.carrier.global.feign.nextcloud.NextcloudShareClient;
import org.example.carrier.global.feign.nextcloud.dto.response.ShareUrlResponse;
import org.example.carrier.global.utils.dto.response.UploadFileResponse;
import org.springframework.core.io.Resource;
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
    private final NextcloudFilesClient nextcloudFilesClient;
    private final NextcloudShareClient nextcloudShareClient;

    private final String fileNameTemplate = "%s-%s-%s";

    public Resource getFile(String fileName) {
        String authHeader = getAuthHeader();

        return nextcloudFilesClient.getFile(fileName, authHeader);
    }

    public UploadFileResponse uploadFile(MultipartFile file, Long userId) throws IOException {
        String fileName = fileNameTemplate
                .formatted(UUID.randomUUID(), userId, file.getOriginalFilename());
        byte[] fileData = file.getBytes();

        String authHeader = getAuthHeader();

        nextcloudFilesClient.uploadFile(fileName, fileData, authHeader);

        ShareUrlResponse shareUrlResponse = nextcloudShareClient.createPublicShare(
                authHeader, "true", "application/json",
                "/" + fileName, 3, 1);

        return new UploadFileResponse(
                fileName,
                shareUrlResponse.getUrl()
                        .replace(nextCloudProperties.getBaseUrl(), "https://nas.anys.kro.kr")
                        + "/download"
        );
    }

    private String getAuthHeader() {
        return "Basic " + Base64.getEncoder()
                .encodeToString((nextCloudProperties.getUsername() + ":" + nextCloudProperties.getPassword())
                        .getBytes(StandardCharsets.UTF_8));
    }
}
