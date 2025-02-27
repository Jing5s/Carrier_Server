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
        val response = new ErrorResponse(ErrorCode.NOT_SUPPORTED_METHOD_ERROR);
        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.code())
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException() {
        val response = new ErrorResponse(ErrorCode.NOT_SUPPORTED_URI_ERROR);
        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.code())
        );
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MismatchedInputException.class,
    })
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException() {
        val response = new ErrorResponse(ErrorCode.VALIDATION_ERROR);
        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.code())
        );
    }

    @ExceptionHandler({
            UnsatisfiedServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            MissingServletRequestParameterException.class,
            MultipartException.class,
    })
    public ResponseEntity<ErrorResponse> handleUnsatisfiedServletRequestParameterException() {
        val response = new ErrorResponse(ErrorCode.VALIDATION_ERROR);
        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.code())
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException() {
        val response = new ErrorResponse(ErrorCode.VALIDATION_ERROR);
        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.code())
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException() {
        val response = new ErrorResponse(ErrorCode.VALIDATION_ERROR);
        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.code())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAnonymousException() {
        val response = new ErrorResponse(ErrorCode.UNEXPECTED_SERVER_ERROR);
        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.code())
        );
    }

    private Map<String, Map<String, String>> getErrorsMap(Map<String, String> errors) {
        Map<String, Map<String, String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}