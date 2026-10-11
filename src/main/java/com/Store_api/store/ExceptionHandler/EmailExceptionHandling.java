package com.Store_api.store.ExceptionHandler;

import com.Store_api.store.Exception.EmailAlreadyExistsException;
import com.Store_api.store.Exception.EmailNotFoundException;
import com.Store_api.store.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class EmailExceptionHandling {
    @ExceptionHandler({EmailAlreadyExistsException.class, EmailNotFoundException.class})
    public ResponseEntity<ErrorDto> handleEmailExist(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto(ex.getMessage())
        );
    }
}
