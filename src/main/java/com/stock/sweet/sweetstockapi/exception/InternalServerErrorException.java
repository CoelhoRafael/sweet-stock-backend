package com.stock.sweet.sweetstockapi.exception;

public class InternalServerErrorException extends Exception{
    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
