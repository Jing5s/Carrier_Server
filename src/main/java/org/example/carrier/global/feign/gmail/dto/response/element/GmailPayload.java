package org.example.carrier.global.feign.gmail.dto.response.element;

import org.example.carrier.global.config.deserializer.GmailDateDeserializer;

import java.time.LocalDateTime;
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
        return getHeaderValue(headers, "To");
    }

    public String getSubject() {
        return getHeaderValue(headers, "Subject");
    }

    public LocalDateTime getDate() {
        String dateStr = getHeaderValue(headers, "Date");
        return GmailDateDeserializer.parse(dateStr);
    }

    private static String getHeaderValue(List<GmailHeaders> headers, String headerName) {
        return headers.stream()
                .filter(header -> header.name().equalsIgnoreCase(headerName))
                .map(GmailHeaders::value)
                .findFirst()
                .orElse(null);
    }
}
