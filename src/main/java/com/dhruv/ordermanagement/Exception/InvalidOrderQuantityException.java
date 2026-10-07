package com.dhruv.ordermanagement.Exception;

public class InvalidOrderQuantityException extends RuntimeException{

    public InvalidOrderQuantityException(int quantity){
        super("Invalid order quantity: " + quantity + ". Quantity must be greater than 0.");
    }
}
