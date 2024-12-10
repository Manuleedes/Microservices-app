package com.Lidigu.ecommerce.handler;

import com.Lidigu.ecommerce.exception.CustomerNotFoundException;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException exp) {
        return ResponseEntity
                .status(HttpStatus.SC_NOT_FOUND)
                .body(exp.getMsg());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<errorResponse> handle(MethodArgumentNotValidException exp) {
        var errors= new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error ->{
                     var fieldName= ((FieldError)error).getField();
                     var errorMessage= error.getDefaultMessage();
                     errors.put(fieldName, errorMessage);
                });
        return ResponseEntity
                .status(HttpStatus.SC_BAD_REQUEST)
                .body(new errorResponse(errors));
    }
}
