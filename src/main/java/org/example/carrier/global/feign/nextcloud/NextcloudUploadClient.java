package org.example.carrier.global.feign.nextcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "nextcloud-upload", url = "http://211.112.175.88:30443/remote.php/dav/files/carrier/")
public interface NextcloudUploadClient {

    @PutMapping(value = "/{fileName}", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    void uploadFile(@PathVariable("fileName") String fileName, @RequestBody byte[] fileData, @RequestHeader("Authorization") String authHeader);
}
