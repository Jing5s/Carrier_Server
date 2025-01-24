package org.example.carrier.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // user
    USER_NOT_FOUND(404, "User Not Found."),

    // jwt
    EXPIRED_JWT_TOKEN(401, "Expired Jwt Token"),
    INVALID_JWT_TOKEN(401, "Invalid Jwt Token"),

    // server
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int status;
    private final String message;
}
