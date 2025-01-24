package org.example.carrier.global.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.example.carrier.global.feign.exception.InternalServerException;
import org.example.carrier.global.feign.exception.InvalidAuthTokenException;

public class FeignClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 400 -> throw InvalidAuthTokenException.EXCEPTION;
        }
        throw InternalServerException.EXCEPTION;
    }
}
