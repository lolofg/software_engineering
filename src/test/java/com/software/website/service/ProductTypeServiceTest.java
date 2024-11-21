package com.software.website.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.software.website.Entity.ProductType;
import com.software.website.RowMapper.ProductTypeRowMapper;

public class ProductTypeServiceTest {

    @InjectMocks
    private ProductTypeService productTypeService;

    @Mock
    private JdbcTemplate jdbcTemplate; 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProductTypes() {

        ProductType productType = new ProductType(); 
        productType.setProductTypeID(24);
        productType.setProductTypeName("PS5");

        List<ProductType> expectedProductTypes = Collections.singletonList(productType);

        when(jdbcTemplate.query(eq("select * from ProductType"), any(ProductTypeRowMapper.class)))
        .thenReturn(expectedProductTypes);

        List<ProductType> actualProductTypes = productTypeService.getAllProductTypes();

        verify(jdbcTemplate).query(eq("select * from ProductType"), any(ProductTypeRowMapper.class));
        assertEquals(expectedProductTypes, actualProductTypes);

    }

    @Test
    public void testGetAllProductTypesNoData() {
        
        when(jdbcTemplate.query(eq("select * from ProductType"), any(ProductTypeRowMapper.class)))
        .thenReturn(Collections.emptyList());

        List<ProductType> actualProductTypes = productTypeService.getAllProductTypes();

        verify(jdbcTemplate).query(eq("select * from ProductType"), any(ProductTypeRowMapper.class));

        assertTrue(actualProductTypes.isEmpty());
    }
    
}
