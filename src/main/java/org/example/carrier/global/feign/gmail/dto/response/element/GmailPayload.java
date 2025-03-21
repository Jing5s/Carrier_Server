package org.example.carrier.global.feign.gmail.dto.response.element;

import java.util.List;

public record GmailPayload(
        String mimeType,
        List<GmailHeaders> headers,
        List<GmailParts> parts,
        GmailPartsBody body
) {
    public String getFrom() {
        return getHeaderValue(headers, "From");
    }

    public String getTo() {
        return getHeaderValue(headers, "Delivered-To");
    }

    public String getSubject() {
        return getHeaderValue(headers, "Subject");
    }

    public String getBody() {
        if (body != null && body.data() != null && !body.data().isBlank()) {
            return body.data();
        }

        return findBody(parts);
    }

    private static String getHeaderValue(List<GmailHeaders> headers, String headerName) {
        return headers.stream()
                .filter(header -> header.name().equalsIgnoreCase(headerName))
                .map(GmailHeaders::value)
                .findFirst()
                .orElse(null);
    }

    private String findBody(List<GmailParts> parts) {
        String textPlain = null;

        for (GmailParts part : parts) {
            if (part.body() != null && part.body().data() != null && !part.body().data().isBlank()) {
                if ("text/plain".equalsIgnoreCase(part.mimeType())) {
                    textPlain = part.body().data();
                } else if ("text/html".equalsIgnoreCase(part.mimeType())) {
                    return part.body().data();
                }
            }

            if (part.parts() != null) {
                return findBody(part.parts());
            }
        }

        return textPlain;
    }
}
