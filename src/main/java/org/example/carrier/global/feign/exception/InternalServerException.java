package org.example.carrier.global.feign.exception;

import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class InternalServerException extends GlobalException {
    public static final GlobalException EXCEPTION = new InternalServerException();

    private InternalServerException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
