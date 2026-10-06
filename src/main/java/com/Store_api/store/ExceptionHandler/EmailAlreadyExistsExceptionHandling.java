package com.Store_api.store.ExceptionHandler;

import com.Store_api.store.Exception.EmailAlreadyExistsException;
import com.Store_api.store.Exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class EmailAlreadyExistsExceptionHandling {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailExist(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Email already exists")
        );
    }
}
