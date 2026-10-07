package com.dhruv.ordermanagement.Exception;

public class InvalidOrderException extends RuntimeException{

    public InvalidOrderException(String message){
        super(message);
    }
}
