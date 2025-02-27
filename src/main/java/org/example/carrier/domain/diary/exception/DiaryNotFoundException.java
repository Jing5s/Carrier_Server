package org.example.carrier.domain.diary.exception;

import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class DiaryNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new DiaryNotFoundException();

    private DiaryNotFoundException() {
        super(ErrorCode.DIARY_NOT_FOUND);
    }
}
