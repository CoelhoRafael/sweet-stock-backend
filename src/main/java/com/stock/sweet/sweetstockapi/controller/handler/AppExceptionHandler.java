package com.stock.sweet.sweetstockapi.controller.handler;

import com.stock.sweet.sweetstockapi.exception.*;
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

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorObject> createError(InternalServerErrorException e) {
        ErrorObject errorObject = new ErrorObject(LocalDateTime.now(), e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorObject);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorObject> createError(BadRequestException e) {
        ErrorObject errorObject = new ErrorObject(LocalDateTime.now(), e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorObject);
    }
}
