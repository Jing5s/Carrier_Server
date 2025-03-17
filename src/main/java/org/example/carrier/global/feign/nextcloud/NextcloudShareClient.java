package org.example.carrier.global.feign.nextcloud;

import org.example.carrier.global.feign.nextcloud.dto.response.ShareUrlResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nextcloud-share", url = "${nextcloud.share-base-url}")
public interface NextcloudShareClient {

    @PostMapping("/ocs/v2.php/apps/files_sharing/api/v1/shares")
    ShareUrlResponse createPublicShare(
            @RequestHeader("Authorization") String authHeader,
            @RequestHeader("OCS-APIRequest") String ocsHeader,
            @RequestHeader("Accept") String acceptHeader,
            @RequestParam("path") String path,
            @RequestParam("shareType") int shareType,
            @RequestParam("permissions") int permissions
    );
}
