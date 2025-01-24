package org.example.carrier.global.feign.exception;

import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class InvalidAuthTokenException extends GlobalException {
    public static final GlobalException EXCEPTION = new InvalidAuthTokenException();

    private InvalidAuthTokenException() {
        super(ErrorCode.INVALID_AUTH_TOKEN);
    }
}
