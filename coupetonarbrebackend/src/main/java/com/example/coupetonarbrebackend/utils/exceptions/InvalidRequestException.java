package com.example.coupetonarbrebackend.utils.exceptions;

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(String message) {
        super(message);
    }
}