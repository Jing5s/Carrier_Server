package org.example.carrier.global.error;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.example.carrier.global.error.exception.ErrorCode;
import org.example.carrier.global.error.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandling(GlobalException e) {
        final ErrorCode errorCode = e.getErrorCode();

        return new ResponseEntity<>(
                new ErrorResponse(errorCode),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Map<String, String>>> handleBadRequestExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException() {
        final ErrorCode errorCode = ErrorCode.NOT_SUPPORTED_METHOD_ERROR;
        return new ResponseEntity<>(
                new ErrorResponse(errorCode),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException() {
        final ErrorCode errorCode = ErrorCode.NOT_SUPPORTED_URI_ERROR;
        return new ResponseEntity<>(
                new ErrorResponse(errorCode),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MismatchedInputException.class,
    })
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException() {
        final ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        return new ResponseEntity<>(
                new ErrorResponse(errorCode),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler({
            UnsatisfiedServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            MissingServletRequestParameterException.class,
            MultipartException.class,
    })
    public ResponseEntity<ErrorResponse> handleUnsatisfiedServletRequestParameterException() {
        final ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        return new ResponseEntity<>(
                new ErrorResponse(errorCode),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException() {
        final ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        return new ResponseEntity<>(
                new ErrorResponse(errorCode),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException() {
        final ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        return new ResponseEntity<>(
                new ErrorResponse(errorCode),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAnonymousException() {
        final ErrorCode errorCode = ErrorCode.UNEXPECTED_SERVER_ERROR;
        return new ResponseEntity<>(
                new ErrorResponse(errorCode),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    private Map<String, Map<String, String>> getErrorsMap(Map<String, String> errors) {
        Map<String, Map<String, String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}