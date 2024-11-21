package com.software.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.software.website.Entity.ProductType;
import com.software.website.service.ProductTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService; 

    @GetMapping("/allProductTypes")
    public List<ProductType> getAllProductTypes() {
        return productTypeService.getAllProductTypes();
    }

    @GetMapping("/OneProductType/{id}")
    public ProductType getOneProductTypeById(@PathVariable int id) {
        return productTypeService.getOneProductTypeByID(id);
    }
    

}
