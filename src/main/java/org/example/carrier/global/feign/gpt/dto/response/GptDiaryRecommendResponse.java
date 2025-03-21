package org.example.carrier.global.feign.gpt.dto.response;

import java.util.List;

public record GptDiaryRecommendResponse(
        List<String> recommend
) implements GptMessageResponse {
}
