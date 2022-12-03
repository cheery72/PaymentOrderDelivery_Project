package com.project.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ServiceExceptionHandler {

    @ExceptionHandler(NotPaymentPointException.class)
    protected ResponseEntity NotPaymentPointException(NotPaymentPointException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder().code("Point Insufficient").message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

    }

    @ExceptionHandler(NotPaymentCardException.class)
    protected ResponseEntity NotPaymentCardException(NotPaymentCardException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder().code("Money Insufficient").message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NotFindMemberException.class)
    protected ResponseEntity NotFindMemberException(NotFindMemberException e){
        final ErrorResponse errorResponse = ErrorResponse.builder().code("Not find Member").message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
