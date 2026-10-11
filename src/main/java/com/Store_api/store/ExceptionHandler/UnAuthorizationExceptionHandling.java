package com.Store_api.store.ExceptionHandler;

import com.Store_api.store.Exception.UnAuthorizationException;
import com.Store_api.store.Exception.UserNotFoundException;
import com.Store_api.store.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
@ControllerAdvice

public class UnAuthorizationExceptionHandling {
    @ExceptionHandler(UnAuthorizationException.class)
    public ResponseEntity<ErrorDto> handleUserNotFound(Exception ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorDto(ex.getMessage())
        );
    }
}
