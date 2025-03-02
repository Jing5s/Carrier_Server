package org.example.carrier.global.feign.gmail.dto.response.element;

import java.util.List;

public record GmailHistory(
        String id,
        List<GmailListDetail> messages
) {
    public String getMailId() {
        return messages.get(0).id();
    }
}
