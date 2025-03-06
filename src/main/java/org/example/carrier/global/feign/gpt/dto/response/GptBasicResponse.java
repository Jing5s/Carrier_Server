package org.example.carrier.global.feign.gpt.dto.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public record GptBasicResponse(
        List<GptChoicesElement> choices
) {
    private record GptChoicesElement(
            GptMessageElement message
    ) {}

    private record GptMessageElement(
            String content
    ) {}

    public GptMessageResponse getResponse(ObjectMapper objectMapper, Class<? extends GptMessageResponse> gptMessageResponse) throws JsonProcessingException {
        return objectMapper.readValue(choices.get(0).message.content, gptMessageResponse);
    }
}


