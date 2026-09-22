package com.dhruv.ordermanagement.Entity;

import com.dhruv.ordermanagement.Entity.Customer;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private Date orderDate;
    private String status;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}