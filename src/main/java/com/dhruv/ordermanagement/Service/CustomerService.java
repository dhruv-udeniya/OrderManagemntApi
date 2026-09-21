package com.dhruv.ordermanagement.Service;


import com.dhruv.ordermanagement.Entity.Customer;
import com.dhruv.ordermanagement.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }


    public Customer addCustomers(Customer customer){
        return customerRepository.save(customer);
    }


    public void deleteCustomerById(long id){
        if (!customerRepository.existsById(id)){
            System.out.println("Id not found.");
            return;
        }
        customerRepository.deleteById(id);
    }

    public Customer updateCustomerById(long id,Customer updatecustomer){

        if (!customerRepository.existsById(id)){
            System.out.println("Customer Not Found.");
            return null;
        }

        Customer customer = customerRepository.findById(id).get();

        customer.setName(updatecustomer.getName());
        customer.setEmail(updatecustomer.getEmail());
        customer.setPhone(updatecustomer.getPhone());

        return customerRepository.save(customer);
    }

}
