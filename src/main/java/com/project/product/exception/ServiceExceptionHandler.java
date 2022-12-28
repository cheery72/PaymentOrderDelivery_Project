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

    @ExceptionHandler(NotFoundMemberException.class)
    protected ResponseEntity NotFoundMemberException(NotFoundMemberException e){
        final ErrorResponse errorResponse = ErrorResponse.builder().code("Not found Member").message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NotFoundStoreException.class)
    protected ResponseEntity NotFoundStoreException(NotFoundStoreException e){
        final ErrorResponse errorResponse = ErrorResponse.builder().code("Not found Store").message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NotFoundOrderException.class)
    protected ResponseEntity NotFoundOrderException(NotFoundOrderException e){
        final ErrorResponse errorResponse = ErrorResponse.builder().code("Not found Order").message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NotFoundDriverException.class)
    protected ResponseEntity NotFoundDriverException(NotFoundOrderException e){
        final ErrorResponse errorResponse = ErrorResponse.builder().code("Not found driver").message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NotFoundDeliveryException.class)
    protected ResponseEntity NotFoundDeliveryException(NotFoundDeliveryException e){
        final ErrorResponse errorResponse = ErrorResponse.builder().code("Not found delivery").message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
