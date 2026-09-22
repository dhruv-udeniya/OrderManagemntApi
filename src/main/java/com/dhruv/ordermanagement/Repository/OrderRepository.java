package com.dhruv.ordermanagement.Repository;

import com.dhruv.ordermanagement.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
