package com.dhruv.ordermanagement.Controller;

import com.dhruv.ordermanagement.Entity.Product;
import com.dhruv.ordermanagement.Repository.ProductRepository;
import com.dhruv.ordermanagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController{


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addProduct(product));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        return ResponseEntity.ok(service.getProductById(id));
    }


    @GetMapping("/search")
    public ResponseEntity<Product> getProduct(@RequestParam String name){
        return ResponseEntity.ok(service.getProductByName(name));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam String category){
        return ResponseEntity.ok(service.getProductByCategory(category));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable long id){
        service.deleteProducts(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProducts(@PathVariable long id,@RequestBody Product product){
        return ResponseEntity.ok(service.updateProduct(id,product));
    }
}