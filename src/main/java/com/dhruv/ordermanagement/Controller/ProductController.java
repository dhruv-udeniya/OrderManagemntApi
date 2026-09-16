package com.dhruv.ordermanagement.Controller;

import com.dhruv.ordermanagement.Entity.Product;
import com.dhruv.ordermanagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController{


    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    @GetMapping("/")
    public String getProducts(){
        return "All Products";
    }



    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productRepository.save(product);
    }


    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id){
        return "This is our Product it id is-:"+id;
    }


    @GetMapping("/search")
    public String getProduct(@RequestParam String name){
        return "Searching product-:"+name;
    }
}