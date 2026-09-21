package com.dhruv.ordermanagement.Controller;


import com.dhruv.ordermanagement.Entity.Customer;
import com.dhruv.ordermanagement.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    CustomerService service;


    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id){
        return ResponseEntity.ok(service.customerGetById(id));
    }


    @GetMapping("/search")
    public ResponseEntity<Customer> getCustomerByName(@RequestParam String name){
        return ResponseEntity.ok(service.customerGetByName(name));
    }


    @PostMapping
    public ResponseEntity<Customer> addCustomers(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addCustomers(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable long id){
        service.deleteCustomerById(id);
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id,@RequestBody Customer customer){
        return  ResponseEntity.ok(service.updateCustomerById(id,customer));
    }

}
