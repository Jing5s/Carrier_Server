package org.example.carrier.global.feign.gmail.dto.response;

import org.example.carrier.global.feign.gmail.dto.response.element.GmailPayload;

import java.util.List;

public record GmailDetailResponse(
        String id,
        String threadId,
        List<String> labelIds,
        String snippet,
        GmailPayload payload,
        Long historyId
) {
    public Boolean isRead() {
        return !labelIds.contains("UNREAD");
    }
}
