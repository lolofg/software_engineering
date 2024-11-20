package com.software.website.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import com.software.website.Entity.ProductType;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductTypeRowMapper implements RowMapper<ProductType> {
    @Override
    public ProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductType obj = new ProductType(); 
        obj.setProductTypeID(rs.getInt("ProductTypeID"));
        obj.setProductTypeName(rs.getString("ProductTypeName"));
        return obj; 
    }

}
