package com.dhruv.ordermanagement.Controller;

import com.dhruv.ordermanagement.Entity.Product;
import com.dhruv.ordermanagement.Repository.ProductRepository;
import com.dhruv.ordermanagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }


    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return service.addProduct(product);
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id){
        return service.getProductById(id);
    }


    @GetMapping("/search")
    public Product getProduct(@RequestParam String name){
        return service.getProductByName(name);
    }

    @GetMapping("/category")
    public List<Product> getProductByCategory(@RequestParam String category){
        return service.getProductByCategory(category);
    }


    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable long id){
        service.deleteProducts(id);
        return "Product Delete Successfully";
    }

    @PutMapping("/{id}")
    public Product updateProducts(@PathVariable long id,@RequestBody Product product){
        return service.updateProduct(id,product);
    }
}