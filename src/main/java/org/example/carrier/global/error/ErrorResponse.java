package org.example.carrier.global.error;

import org.example.carrier.global.error.exception.ErrorCode;

public record ErrorResponse(
        int status,
        String code,
        String message
) {
    public ErrorResponse(ErrorCode errorCode) {
        this(
                errorCode.getStatus(),
                errorCode.getCode(),
                errorCode.getMessage()
        );
    }
}
