package com.dhruv.ordermanagement.Controller;


import com.dhruv.ordermanagement.Entity.Customer;
import com.dhruv.ordermanagement.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    CustomerService service;


    @GetMapping
    public List<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable long id){
        return service.customerGetById(id);
    }


    @GetMapping("/search")
    public Customer getCustomerByName(@RequestParam String name){
        return service.customerGetByName(name);
    }


    @PostMapping
    public Customer addCustomers(@RequestBody Customer customer){
        return service.addCustomers(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable long id){
        service.deleteCustomerById(id);
    }


    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable long id,@RequestBody Customer customer){
        return  service.updateCustomerById(id,customer);
    }

}
