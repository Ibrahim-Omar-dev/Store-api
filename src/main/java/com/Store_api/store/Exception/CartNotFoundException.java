package com.Store_api.store.Exception;

import com.fasterxml.jackson.databind.util.ClassUtil;

public class CartNotFoundException extends RuntimeException{
   public CartNotFoundException(){
        super("Cart not found");
    }
}
