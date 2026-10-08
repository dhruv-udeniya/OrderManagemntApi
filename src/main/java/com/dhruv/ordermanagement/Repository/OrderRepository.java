package com.dhruv.ordermanagement.Repository;

import com.dhruv.ordermanagement.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByCustomer_Id(Long customerId);

}
