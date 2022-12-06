package com.stock.sweet.sweetstockapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleException(Exception exception){
//        log.info("Recebendo tratativa de exception no metodo handleException -> Exception");
//
//        DefaultError defaultError = new DefaultError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(defaultError);
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity handleException(IllegalArgumentException exception){
//        log.info("Recebendo tratativa de exception no metodo handleException -> IllegalArgumentException");
//
//        DefaultError defaultError = new DefaultError(422, exception.getMessage());
//
//        return ResponseEntity.status(422).body(defaultError);
//    }
}
