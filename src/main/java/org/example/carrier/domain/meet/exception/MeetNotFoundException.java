package org.example.carrier.domain.meet.exception;

import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class MeetNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new MeetNotFoundException();

    private MeetNotFoundException() {
        super(ErrorCode.MEET_NOT_FOUND);
    }
}
