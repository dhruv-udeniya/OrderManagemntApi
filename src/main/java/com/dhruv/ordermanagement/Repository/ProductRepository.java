package com.dhruv.ordermanagement.Repository;

import com.dhruv.ordermanagement.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
