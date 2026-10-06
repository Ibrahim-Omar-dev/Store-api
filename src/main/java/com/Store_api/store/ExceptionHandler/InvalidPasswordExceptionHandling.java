package com.Store_api.store.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;

@ControllerAdvice
public class InvalidPasswordExceptionHandling {
    public ResponseEntity<Map<String,String>> HandleInvalidPassword()
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("error", "Invalid Password")
        );
    }
}
