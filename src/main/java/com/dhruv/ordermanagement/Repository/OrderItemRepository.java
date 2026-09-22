package com.dhruv.ordermanagement.Repository;

import com.dhruv.ordermanagement.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {


}
