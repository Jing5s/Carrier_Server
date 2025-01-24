package org.example.carrier.global.security.exception;

import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class ExpiredJwtTokenException extends GlobalException {
    public static final GlobalException EXCEPTION = new ExpiredJwtTokenException();

    private ExpiredJwtTokenException() {
        super(ErrorCode.EXPIRED_JWT_TOKEN);
    }
}
