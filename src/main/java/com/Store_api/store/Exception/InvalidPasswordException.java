package com.Store_api.store.Exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(){
        super("Invalid Password");
    }
}
