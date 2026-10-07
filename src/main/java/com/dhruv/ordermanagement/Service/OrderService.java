package com.dhruv.ordermanagement.Service;

import java.util.Date;
import com.dhruv.ordermanagement.Entity.Customer;
import com.dhruv.ordermanagement.Entity.Order;
import com.dhruv.ordermanagement.Entity.OrderItem;
import com.dhruv.ordermanagement.Entity.Product;
import com.dhruv.ordermanagement.Exception.*;
import com.dhruv.ordermanagement.Repository.CustomerRepository;
import com.dhruv.ordermanagement.Repository.OrderItemRepository;
import com.dhruv.ordermanagement.Repository.OrderRepository;
import com.dhruv.ordermanagement.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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



    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }


    @Transactional
    public Order createOrder(Order order){

        // Get the Customer object that came inside the Order request from Postman
        Customer customer = order.getCustomer();

        // Check whether a customer was provided
        if (customer == null) {
            throw new InvalidOrderException("Customer is required.");
        }

        // Get the Customer ID from that Customer object
        Long customerId = customer.getId();

        if (customerId == null) {
            throw new InvalidOrderException("Customer ID is required.");
        }

        // Use the Customer ID to find the actual Customer from the MySQL database
        // findById() returns Optional<Customer>, so .get() extracts the Customer
        Customer customerFromDB = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        // Attach the actual Customer from the database to this Order
        order.setCustomer(customerFromDB);

        // Get all OrderItems that came inside the Order request
        List<OrderItem> items = order.getItems();

        // Check whether the Order contains at least one item
        if (items == null || items.isEmpty()) {

            throw new InvalidOrderException("Order must contain at least one item.");
        }

        // Store the total of all items
        double totalAmount = 0;

        // Process every OrderItem inside this Order
        for (OrderItem item : items) {

            // Check whether the OrderItem is null
            if (item == null) {
                throw new InvalidOrderException("Order item cannot be null.");
            }

            if (item.getQuantity() <= 0) {
                throw new InvalidOrderQuantityException(item.getQuantity());
            }

            // Get the Product object from this OrderItem
            Product product = item.getProduct();

            // Check whether a product was provided
            if (product == null) {
                throw new InvalidOrderException("Product is required.");
            }


            // Get the Product ID
            Long productId = product.getId();

            // Check whether Product ID was provided
            if (productId == null) {
                throw new InvalidOrderException("Product ID is required.");
            }

            // Find the actual Product from the database or else throw exception
            Product productFromDB = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));

            // Check whether enough stock is available
            if (productFromDB.getStock() < item.getQuantity()) {

                throw new InsufficientStockException(
                        productId,
                        productFromDB.getStock(),
                        item.getQuantity()
                );
            }

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

            // Get the Product attached to this OrderItem
            Product product = item.getProduct();

            // Calculate remaining stock
            int newStock = product.getStock() - item.getQuantity();

            // Update Product stock
            product.setStock(newStock);

            // Save updated Product
            productRepository.save(product);

            // Save this OrderItem
            orderItemRepository.save(item);


        }



        // Return the Order object back to the Controller
        return order;
    }

}
