package com.dhruv.ordermanagement.Service;

import com.dhruv.ordermanagement.Entity.Product;
import com.dhruv.ordermanagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {


    @Autowired
    ProductRepository repo;


    public List<Product> getAllProducts(){
        return repo.findAll();
    }


    public Product addProduct(Product product){
        return repo.save(product);
    }


    public Product getProductById(long id){
        if (!repo.existsById(id)){
            System.out.println("Product not Found.");
        }
        return repo.findById(id).get();
    }

    public Product getProductByName(String name){
        Product product = repo.findByName(name);
        if (product == null){
            System.out.println("Product not Found.");
            return null;
        }
        return product;
    }


    public List<Product> getProductByCategory(String category){
        return repo.findByCategory(category);
    }

    public void deleteProducts(long id){
        if (!repo.existsById(id)){
            System.out.println("Id Not Found..");
        }
        repo.deleteById(id);
    }

    public Product updateProduct(long id, Product updatedProduct) {

        if (!repo.existsById(id)) {
            System.out.println("Product not Found..");
            return null;
        }

        Product product = repo.findById(id).get();

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        product.setCategory(updatedProduct.getCategory());

        return repo.save(product);
    }

}
