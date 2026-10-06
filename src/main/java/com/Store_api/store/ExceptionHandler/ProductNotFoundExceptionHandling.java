package com.Store_api.store.ExceptionHandler;

import com.Store_api.store.Exception.CartNotFoundException;
import com.Store_api.store.Exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

public class ProductNotFoundExceptionHandling {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleCartNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "product not found in the cart")
        );
    }
}
