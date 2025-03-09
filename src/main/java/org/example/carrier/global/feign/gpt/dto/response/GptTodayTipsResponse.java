package org.example.carrier.global.feign.gpt.dto.response;

import java.util.List;

public record GptTodayTipsResponse(
        List<String> tips
) implements GptMessageResponse {
}
