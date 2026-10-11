package com.Store_api.store.ExceptionHandler;

import com.Store_api.store.Exception.*;
import com.Store_api.store.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
@ControllerAdvice

public class NotFoundExceptionHandling {
    @ExceptionHandler({ProductNotFoundException.class, CartNotFoundException.class,
    EmailNotFoundException.class, UserNotFoundException.class, CategoryNotFoundException.class})
    public ResponseEntity<ErrorDto> handleCartNotFound(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto(ex.getMessage())
        );
    }
}
