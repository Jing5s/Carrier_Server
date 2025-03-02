package org.example.carrier.global.feign.gmail.dto.response;

import org.example.carrier.global.feign.gmail.dto.response.element.GmailHistory;

import java.util.List;

public record GmailHistoryResponse(
        List<GmailHistory> history
) {
}
