package com.groceryStore.groceryStore.exception;

public class GroceryNotFoundException extends RuntimeException{
    public GroceryNotFoundException(){};

    public GroceryNotFoundException(String message){
        super(message);
    }
}
