package com.Store_api.store.ExceptionHandler;

import com.Store_api.store.Exception.CategoryNotFoundException;
import com.Store_api.store.dto.Product.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class CategoryNotFoundExceptionHandling {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleCategoryNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Category Not Found!")
        );    }
}
