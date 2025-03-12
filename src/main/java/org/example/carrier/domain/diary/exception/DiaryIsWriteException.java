package org.example.carrier.domain.diary.exception;

import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class DiaryIsWriteException extends GlobalException {
    public static final GlobalException EXCEPTION = new DiaryIsWriteException();

    private DiaryIsWriteException() {
        super(ErrorCode.DIARY_IS_WRITE);
    }
}
