package com.Store_api.store.Exception;

public class EmailAlreadyExistsException extends RuntimeException
{
    public  EmailAlreadyExistsException()
    {
        super("Email already exists");
    }
}
