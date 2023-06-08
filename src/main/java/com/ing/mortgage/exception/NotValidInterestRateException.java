package com.ing.mortgage.exception;

public class NotValidInterestRateException extends RuntimeException {
    public NotValidInterestRateException(String message) {
        super(message);
    }
}
