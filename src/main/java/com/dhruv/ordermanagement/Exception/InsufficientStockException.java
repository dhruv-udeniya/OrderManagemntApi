package com.dhruv.ordermanagement.Exception;

public class InsufficientStockException extends RuntimeException{

    public InsufficientStockException(Long productId, int availableStock, int requestedQuantity) {
        super("Insufficient stock for product id: " + productId
                + ". Available: " + availableStock
                + ", Requested: " + requestedQuantity);
    }
}
