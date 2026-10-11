package com.Store_api.store.ExceptionHandler;

import com.Store_api.store.Exception.EmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EmailNotFoundExceptionHandling {
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleEmailNotFoundException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body (Map.of("error", "Email not exists"));
    }
}
