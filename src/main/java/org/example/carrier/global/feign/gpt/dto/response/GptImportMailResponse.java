package org.example.carrier.global.feign.gpt.dto.response;

public record GptImportMailResponse(
        String gmailId,
        Boolean important
) {
}
