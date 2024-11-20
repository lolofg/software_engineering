package com.software.website.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import com.software.website.Entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product obj = new Product();
        obj.setProductID(rs.getInt("ProductID"));
        obj.setProductName(rs.getString("ProductName"));
        obj.setProductTypeID(rs.getInt("ProductTypeID"));
        return obj;
    }
    
}
