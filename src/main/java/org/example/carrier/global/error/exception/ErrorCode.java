package org.example.carrier.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // user
    USER_NOT_FOUND(404, "USER-404-1", "User Not Found."),

    // jwt
    EXPIRED_JWT_TOKEN(401, "JWT-401-1", "Expired Jwt Token"),
    INVALID_JWT_TOKEN(401, "JWT-401-2", "Invalid Jwt Token"),

    // auth
    INVALID_AUTH_TOKEN(400, "AUTH-400-1", "Invalid Auth Token"),

    // server
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal server error");

    private final int status;
    private final String code;
    private final String message;
}
