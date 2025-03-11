package org.example.carrier.global.feign.gmail.dto.response.element;

import java.util.List;

public record GmailListDetail(
        String id,
        String threadId,
        List<String> labelIds
) {
}
