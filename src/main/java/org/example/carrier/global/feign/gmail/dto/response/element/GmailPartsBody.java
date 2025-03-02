package org.example.carrier.global.feign.gmail.dto.response.element;

import org.apache.commons.codec.binary.Base64;

public record GmailPartsBody(
        String data
) {
    public GmailPartsBody(String data) {
        if (data == null || data.isBlank()) {
            this.data = data;
        } else {
            byte[] decodedBytes = Base64.decodeBase64(data);
            this.data = new String(decodedBytes);
        }
    }
}
