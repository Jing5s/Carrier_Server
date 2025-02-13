package org.example.carrier.global.feign.gmail.dto.response.element;

public record GmailParts(
        String partId,
        String mimeType,
        GmailPartsBody body
) {
}
