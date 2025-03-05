package org.example.carrier.global.feign.gmail.dto.request;

import java.util.ArrayList;
import java.util.List;

public record ModifyLabelRequest(
        List<String> removeLabelIds
) {
    public ModifyLabelRequest() {
        this(
                new ArrayList<>() {{
                    add("UNREAD");
                }}
        );
    }
}
