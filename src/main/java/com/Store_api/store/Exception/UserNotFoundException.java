package com.Store_api.store.Exception;

import com.Store_api.store.entities.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User not found");
    }
}
