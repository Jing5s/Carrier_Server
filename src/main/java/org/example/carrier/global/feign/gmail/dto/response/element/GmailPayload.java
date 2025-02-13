package org.example.carrier.global.feign.gmail.dto.response.element;

import java.util.List;

public record GmailPayload(
        String mimeType,
        List<GmailHeaders> headers,
        List<GmailParts> parts
) {
}
