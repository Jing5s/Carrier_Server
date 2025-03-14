package org.example.carrier.global.utils;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import lombok.RequiredArgsConstructor;
import org.example.carrier.global.config.properties.NextCloudProperties;
import org.example.carrier.global.feign.nextcloud.NextcloudSharingClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@RequiredArgsConstructor
@Component
public class NextCloudService {
    private final NextCloudProperties nextCloudProperties;
    private final
    private final NextcloudSharingClient nextcloudSharingClient;

    private static final String NEXTCLOUD_URL = "https://your-nextcloud-instance.com/remote.php/dav/files/your-username/";
    private static final String USERNAME = "carrier";
    private static final String PASSWORD = "tomsualvne";

    public String uploadFile(MultipartFile file) throws IOException {
        String fileUrl = NEXTCLOUD_URL + file.getOriginalFilename();
        HttpHeaders headers = new HttpHeaders();

        // Basic Auth 설정
        String auth = USERNAME + ":" + PASSWORD;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        headers.set("Authorization", "Basic " + encodedAuth);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        HttpEntity<byte[]> requestEntity = new HttpEntity<>(file.getBytes(), headers);

        // PUT 요청으로 파일 업로드
        ResponseEntity<String> response = restTemplate.exchange(fileUrl, HttpMethod.PUT, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "파일 업로드 성공: " + fileUrl;
        } else {
            throw new RuntimeException("파일 업로드 실패: " + response.getStatusCode());
        }
    }

}
