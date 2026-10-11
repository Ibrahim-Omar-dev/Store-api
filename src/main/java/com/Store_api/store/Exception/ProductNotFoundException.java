package com.Store_api.store.Exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("product not found");
    }
}
