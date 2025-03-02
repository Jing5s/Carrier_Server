package org.example.carrier.global.feign.gmail.dto.response.element;

import java.util.List;

public record GmailParts(
        String partId,
        String mimeType,
        GmailPartsBody body,
        List<GmailParts> parts
) {
}
