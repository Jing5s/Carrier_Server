package org.example.carrier.global.utils.dto.response;

public record UploadFileResponse(
        String fileName,
        String uploadUrl
) {
}
