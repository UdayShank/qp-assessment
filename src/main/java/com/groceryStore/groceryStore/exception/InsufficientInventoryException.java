package com.groceryStore.groceryStore.exception;

public class InsufficientInventoryException extends RuntimeException{
    public InsufficientInventoryException(){}

    public InsufficientInventoryException(String message){
        super(message);
    }
}
