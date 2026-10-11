package com.Store_api.store.ExceptionHandler;

import com.Store_api.store.Exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class InvalidPasswordExceptionHandling {
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String,String>> HandleInvalidPassword()
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("error", "Invalid Password")
        );
    }
}
