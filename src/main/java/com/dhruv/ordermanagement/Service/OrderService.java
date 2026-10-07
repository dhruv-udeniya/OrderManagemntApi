package com.dhruv.ordermanagement.Service;

import java.util.Date;
import com.dhruv.ordermanagement.Entity.Customer;
import com.dhruv.ordermanagement.Entity.Order;
import com.dhruv.ordermanagement.Entity.OrderItem;
import com.dhruv.ordermanagement.Entity.Product;
import com.dhruv.ordermanagement.Repository.CustomerRepository;
import com.dhruv.ordermanagement.Repository.OrderItemRepository;
import com.dhruv.ordermanagement.Repository.OrderRepository;
import com.dhruv.ordermanagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;



    public Order createOrder(Order order){

        // Get the Customer object that came inside the Order request from Postman
        Customer customer = order.getCustomer();

        // Get the Customer ID from that Customer object
        Long customerId = customer.getId();

        // Use the Customer ID to find the actual Customer from the MySQL database
        // findById() returns Optional<Customer>, so .get() extracts the Customer
        Customer customerFromDB = customerRepository.findById(customerId).get();

        // Attach the actual Customer from the database to this Order
        order.setCustomer(customerFromDB);

        // Get all OrderItems that came inside the Order request
        List<OrderItem> items = order.getItems();

        // Store the total of all items
        double totalAmount = 0;

        // Process every OrderItem inside this Order
        for (OrderItem item : items) {

            // Get the Product object from this OrderItem
            Product product = item.getProduct();

            // Get the Product ID
            Long productId = product.getId();

            // Find the actual Product from the database
            Product productFromDB = productRepository.findById(productId).get();

            // Attach the actual Product from the database to this OrderItem
            item.setProduct(productFromDB);

            // Take the current Product price and store it in this OrderItem
            item.setPrice(productFromDB.getPrice());

            // Calculate this item's total
            double itemTotal = productFromDB.getPrice() * item.getQuantity();

            // Add this item's total to the overall order total
            totalAmount = totalAmount + itemTotal;
        }

        // Put the final total into the Order
        order.setTotalAmount(totalAmount);

        // Set the current date and time when the order is created
        order.setOrderDate(new Date());

        // Set the initial status of a newly created order
        order.setStatus("PENDING");

        // Save the Order
        Order savedOrder = orderRepository.save(order);

        // Now connect and save every OrderItem
        for (OrderItem item : items) {

            // Connect this OrderItem to the saved Order
            item.setOrder(savedOrder);

            // Save this OrderItem
            orderItemRepository.save(item);
        }

        // Return the Order object back to the Controller
        return order;
    }

}
