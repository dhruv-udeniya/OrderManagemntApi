package com.dhruv.ordermanagement.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCustomerNotFound(CustomerNotFoundException ex){
        return ex.getMessage();
    }


    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProductNotFound(ProductNotFoundException productNotFoundException){
        return productNotFoundException.getMessage();
    }


    @ExceptionHandler(InsufficientStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInsufficientStock(InsufficientStockException ex) {
        return ex.getMessage();
    }


    @ExceptionHandler(InvalidOrderQuantityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidOrderQuantity(InvalidOrderQuantityException ex) {
        return ex.getMessage();
    }


    @ExceptionHandler(InvalidOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidOrder(InvalidOrderException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleOrderNotFound(OrderNotFoundException ex){
        return ex.getMessage();
    }


    @ExceptionHandler(InvalidOrderStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidOrderStatus(InvalidOrderStatusException ex) {
        return ex.getMessage();
    }
}
