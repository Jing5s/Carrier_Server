package org.example.carrier.global.feign.gpt.dto.request.element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record GptMessageElement(
        String role,
        List<GptContentElement> content
) {
    public static GptMessageElement of(String role, String text) {
        return new GptMessageElement(
                role,
                new ArrayList<>(Collections.singleton(new GptContentElement("text", text))));
    }
}
