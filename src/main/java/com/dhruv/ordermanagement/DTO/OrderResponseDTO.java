package com.dhruv.ordermanagement.DTO;

import java.util.Date;
import java.util.List;

public class OrderResponseDTO {

    private Long id;
    private Long customerId;
    private Date orderDate;
    private String status;
    private double totalAmount;
    private List<OrderItemResponseDTO> items;

    public OrderResponseDTO(Long id, Long customerId, Date orderDate,
                            String status, double totalAmount,
                            List<OrderItemResponseDTO> items) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItemResponseDTO> getItems() {
        return items;
    }
}
