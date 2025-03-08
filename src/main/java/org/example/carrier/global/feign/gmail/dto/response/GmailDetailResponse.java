package org.example.carrier.global.feign.gmail.dto.response;

import org.example.carrier.global.feign.gmail.dto.response.element.GmailPayload;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public record GmailDetailResponse(
        String id,
        String threadId,
        List<String> labelIds,
        String snippet,
        GmailPayload payload,
        Long historyId,
        String internalDate
) {
    public Boolean isRead() {
        return !labelIds.contains("UNREAD");
    }

    public LocalDateTime getDate() {
        long date = Long.parseLong(internalDate);

        return Instant.ofEpochMilli(date)
                .atZone(ZoneId.of("Asia/Seoul"))
                .toLocalDateTime();
    }
}
