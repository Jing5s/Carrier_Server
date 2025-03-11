package org.example.carrier.global.feign.gmail.dto.response.element;

import java.util.List;

public record GmailHistory(
        String id,
        List<GmailListDetail> messages
) {
    public String getMailId() {
        GmailListDetail gmailListDetail = messages.get(0);
        if (gmailListDetail.labelIds() == null)
            return gmailListDetail.id();
        if (!gmailListDetail.labelIds().contains("INBOX"))
            return null;

        return gmailListDetail.id();
    }
}
