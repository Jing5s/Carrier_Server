package org.example.carrier.global.security.exception;


import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class InvalidJwtTokenException extends GlobalException {
    public static final GlobalException EXCEPTION = new InvalidJwtTokenException();

    private InvalidJwtTokenException() {
        super(ErrorCode.INVALID_JWT_TOKEN);
    }
}
