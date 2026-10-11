package com.Store_api.store.Exception;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException()
    {
        super("Email not found");
    }
}
