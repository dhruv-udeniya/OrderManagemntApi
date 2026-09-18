package com.dhruv.ordermanagement.Repository;

import com.dhruv.ordermanagement.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {



}
