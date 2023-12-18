package com.example.coupetonarbrebackend.configuration.security.controller;

import com.example.coupetonarbrebackend.configuration.security.exceptions.AddingAdminFailed;
import com.example.coupetonarbrebackend.utils.HttpErrorInfo;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Generated
@RestControllerAdvice
public class SecurityControllerAdvice {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(AddingAdminFailed.class)
    public HttpErrorInfo handleErrorWhileCreatingAdmin(AddingAdminFailed ex) {
        return createHttpErrorInfo(ex.httpStatus, ex);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, Exception ex) {
        final String message = ex.getMessage();

        return new HttpErrorInfo(httpStatus, message);
    }

}
