package org.example.carrier.global.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.example.carrier.domain.mail.exception.MailNotFoundException;
import org.example.carrier.global.feign.exception.InternalServerException;
import org.example.carrier.global.feign.exception.InvalidAuthTokenException;

public class FeignClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 404 -> throw MailNotFoundException.EXCEPTION;
            case 400 -> throw InvalidAuthTokenException.EXCEPTION;
            default -> throw InternalServerException.EXCEPTION;
        }
    }
}
