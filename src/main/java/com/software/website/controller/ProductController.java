package com.software.website.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.software.website.Entity.Product;
import com.software.website.service.ProductService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService; 

    @GetMapping("/allProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/OneProduct/{id}")
    public Product getOneProduct(@PathVariable int id) {
        return productService.getOneProductID(id);
    }
    
    
    
}
