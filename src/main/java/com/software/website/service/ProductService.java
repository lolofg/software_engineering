package com.software.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.software.website.Entity.Product;
import com.software.website.RowMapper.ProductRowMapper;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private JdbcTemplate jdbcTemplate; 

    public List<Product> getAllProducts(){
        return jdbcTemplate.query("select * from product", new ProductRowMapper()); 
    }

    public Product getOneProductID(int Product_ID) {
        return jdbcTemplate.queryForObject("select * from product where Product_ID = ?", new ProductRowMapper(), Product_ID); 
    }
}
