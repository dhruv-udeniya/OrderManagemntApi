package com.dhruv.ordermanagement.Exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long customerId){
        super("Customer not found with id-:"+customerId);
    }

}
