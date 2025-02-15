package com.groceryStore.groceryStore.exception;

public class OrderProcessingException extends RuntimeException{
    public OrderProcessingException(){}

    public OrderProcessingException(String message){
         super(message);
    }
}
