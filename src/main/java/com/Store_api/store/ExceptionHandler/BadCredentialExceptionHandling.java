package com.Store_api.store.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadCredentialExceptionHandling {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void>  handleException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
