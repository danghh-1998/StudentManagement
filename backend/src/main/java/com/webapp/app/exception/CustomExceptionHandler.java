package com.webapp.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public final ResponseEntity<ErrorResponse> customHandleException() {
        ErrorResponse errorResponse =  new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(BaseException.statusCode);
        errorResponse.setDetail(BaseException.detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
