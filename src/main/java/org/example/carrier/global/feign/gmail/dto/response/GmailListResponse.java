package org.example.carrier.global.feign.gmail.dto.response;

import org.example.carrier.global.feign.gmail.dto.response.element.GmailListDetail;

import java.util.List;

public record GmailListResponse(
        List<GmailListDetail> messages
) {
}
