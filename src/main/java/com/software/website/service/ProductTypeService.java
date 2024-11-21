package com.software.website.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.software.website.Entity.ProductType;
import com.software.website.RowMapper.ProductTypeRowMapper;

@Service
public class ProductTypeService {
    @Autowired
    private JdbcTemplate jdbcTemplate; 

    public List<ProductType> getAllProductTypes() {
        return jdbcTemplate.query("select * from ProductType", new ProductTypeRowMapper()); 
    }

    public ProductType getOneProductTypeByID(int ProductTypeID){
        return jdbcTemplate.queryForObject("select * from productType where ProductTypeID = ?", new ProductTypeRowMapper(), ProductTypeID);
    }
    
}