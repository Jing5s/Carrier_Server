package org.example.carrier.global.feign.nextcloud;

import org.example.carrier.global.utils.NextcloudFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "nextcloud-sharing", url = "${nextcloud.base-url}", configuration = NextcloudFeignConfiguration.class)
public interface NextcloudSharingClient {

    @PostMapping(value = "/ocs/v2.php/apps/files_sharing/api/v1/shares",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String createShare(@RequestHeader("OCS-APIRequest") String ocsApiRequest,
                       @RequestBody MultiValueMap<String, String> formData);
}