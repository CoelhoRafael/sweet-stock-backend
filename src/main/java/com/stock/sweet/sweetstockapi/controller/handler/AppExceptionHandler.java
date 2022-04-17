package com.stock.sweet.sweetstockapi.controller.handler;

import com.stock.sweet.sweetstockapi.exception.ConflictException;
import com.stock.sweet.sweetstockapi.exception.ErrorObject;
import com.stock.sweet.sweetstockapi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorObject> createError(NotFoundException e) {
        ErrorObject errorObject = new ErrorObject(LocalDateTime.now(), e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorObject);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorObject> createError(ConflictException e) {
        ErrorObject errorObject = new ErrorObject(LocalDateTime.now(), e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorObject);
    }
}