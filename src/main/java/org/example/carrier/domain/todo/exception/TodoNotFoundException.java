package org.example.carrier.domain.todo.exception;

import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class TodoNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new TodoNotFoundException();

    private TodoNotFoundException() {
        super(ErrorCode.TODO_NOT_FOUND);
    }
}
