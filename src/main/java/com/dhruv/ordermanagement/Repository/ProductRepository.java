package com.dhruv.ordermanagement.Repository;

import com.dhruv.ordermanagement.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    Product findByName(String name);

    List<Product> findByCategory(String category);

}
