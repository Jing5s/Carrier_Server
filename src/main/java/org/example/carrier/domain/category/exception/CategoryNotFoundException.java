package org.example.carrier.domain.category.exception;

import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;

public class CategoryNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new CategoryNotFoundException();

    private CategoryNotFoundException() {
        super(ErrorCode.CATEGORY_NOT_FOUND);
    }
}
