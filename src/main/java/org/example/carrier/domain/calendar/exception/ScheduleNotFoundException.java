package org.example.carrier.domain.calendar.exception;

import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class ScheduleNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new ScheduleNotFoundException();

    private ScheduleNotFoundException() {
        super(ErrorCode.SCHEDULE_NOT_FOUND);
    }
}
