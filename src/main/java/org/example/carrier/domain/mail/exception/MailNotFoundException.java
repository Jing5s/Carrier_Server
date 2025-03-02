package org.example.carrier.domain.mail.exception;

import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class MailNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new MailNotFoundException();

    private MailNotFoundException() {
        super(ErrorCode.MAIL_NOT_FOUND);
    }
}
