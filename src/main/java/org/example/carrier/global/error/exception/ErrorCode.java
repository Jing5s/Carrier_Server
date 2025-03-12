package org.example.carrier.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // user
    USER_NOT_FOUND(404, "USER-404-1", "User Not Found."),

    // diary
    DIARY_NOT_FOUND(404, "DIARY-404-1", "Diary Not Found."),
    DIARY_IS_WRITE(400, "DIARY-400-2", "Diary Is Write."),

    // mail
    MAIL_NOT_FOUND(404, "MAIL-404-1", "Mail Not Found."),

    // category
    CATEGORY_NOT_FOUND(404, "CATEGORY-404-1", "Category Not Found."),

    // todo
    TODO_NOT_FOUND(404, "TODO-404-1", "Todo Not Found."),

    // jwt
    EXPIRED_JWT_TOKEN(401, "JWT-401-1", "Expired Jwt Token"),
    INVALID_JWT_TOKEN(401, "JWT-401-2", "Invalid Jwt Token"),

    // auth
    INVALID_AUTH_TOKEN(400, "AUTH-400-1", "Invalid Auth Token"),

    // server
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal server error"),
    NOT_SUPPORTED_METHOD_ERROR(405, "SERVER-405-1", "Method Not Allowed"),
    VALIDATION_ERROR(400, "SERVER-400-1", "Validation Error"),
    NOT_SUPPORTED_URI_ERROR(500, "SERVER-500-2", "URI Not Supported"),
    UNEXPECTED_SERVER_ERROR(500, "SERVER-500-3", "Unexpected Server Error"),
    ;

    private final int status;
    private final String code;
    private final String message;
}
