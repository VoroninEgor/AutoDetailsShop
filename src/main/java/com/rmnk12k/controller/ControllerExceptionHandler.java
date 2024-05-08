package com.rmnk12k.controller;

import com.rmnk12k.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> productNotFound(ProductNotFoundException e) {
        String errorDescription = "Product not found exception";
        return toResponseEntity(e, HttpStatus.NOT_FOUND, errorDescription);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> userNotFound(UserNotFoundException e) {
        String errorDescription = "User not found exception";
        return toResponseEntity(e, HttpStatus.NOT_FOUND, errorDescription);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> cartNotFound(CartNotFoundException e) {
        String errorDescription = "cart not found exception";
        return toResponseEntity(e, HttpStatus.NOT_FOUND, errorDescription);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> orderNotFound(OrderNotFoundException e) {
        String errorDescription = "order not found exception";
        return toResponseEntity(e, HttpStatus.NOT_FOUND, errorDescription);
    }

    @ExceptionHandler(EmailNotUniqException.class)
    public ResponseEntity<ApiErrorResponse> notUiqNameOrEmail(EmailNotUniqException e) {
        String errorDescription = "Not uniq name or email";
        return toResponseEntity(e, HttpStatus.BAD_REQUEST, errorDescription);
    }

    private ResponseEntity<ApiErrorResponse> toResponseEntity(
            Throwable ex, HttpStatus status, String errorDescription
    ) {
        log.warn(errorDescription);
        ApiErrorResponse response
                = ApiErrorResponse.toApiErrorResponse(ex, errorDescription, status.toString());
        return new ResponseEntity<>(response, status);
    }
}
