package com.dhruv.ordermanagement.Exception;

public class InvalidOrderStatusException extends RuntimeException{

    public InvalidOrderStatusException(String message) {
        super(message);
    }

}
