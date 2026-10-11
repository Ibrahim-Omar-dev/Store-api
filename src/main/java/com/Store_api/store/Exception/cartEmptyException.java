package com.Store_api.store.Exception;

public class cartEmptyException extends RuntimeException{
    public cartEmptyException(){
        super("Cart is empty");
    }
}
